package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;
import ru.loolzaaa.tgbot4j.core.exception.ApiRequestException;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * A common interface for those API methods
 * that are requested with a json content-type.
 * <p>
 * Each returned response can be an object
 * or a collection of objects of a specific type,
 * deserialized as JSON
 * <p>
 * Each implementation can (but does not have to) be validated.
 *
 * @param <T> API response type
 * @see Validated
 */

public interface TelegramMethod<T> extends Validated {
    /**
     * Determining the correct return response type.
     * <p>
     * The implementation of this method implies the use
     * of one of the other methods of this interface:
     * <ul>
     *     <li>{@link #deserializeObjectResponse(ObjectMapper, JsonNode, Class)}</li>
     *     <li>{@link #deserializeCollectionResponse(ObjectMapper, JsonNode, Class)}</li>
     * </ul>
     * <p>
     * Only a concrete API method defines the type
     * of the returned object.
     *
     * @param mapper     json mapper for response conversion
     * @param resultNode deserialized JSON response
     * @return response with correct type
     * @implNote Implemented method <b>MUST NOT</b> invoke {@link #deserializeResponse(ObjectMapper, String)}
     * method because of stack overflow!
     */
    T determineResponseType(ObjectMapper mapper, JsonNode resultNode);

    /**
     * Primary deserialization of any API response.
     * <p>
     * Using by {@link MethodSender} implementation.
     * <p>
     * Further determination of the return object type
     * is passed on to the implementation of {@link #determineResponseType(ObjectMapper, JsonNode)}.
     *
     * @param mapper   json mapper for response conversion
     * @param response API response before deserialization
     * @return response with correct type
     * @throws ApiRequestException if request unsuccessful
     * @throws IOException         if json deserializing error
     */
    default T deserializeResponse(ObjectMapper mapper, String response) throws IOException {
        //TODO: check stack trace for determineResponseType method? Prevent stack overflow?
        ResponseWrapper responseWrapper = mapper.readValue(response, ResponseWrapper.class);
        if (responseWrapper.getOk()) {
            return determineResponseType(mapper, responseWrapper.getResult());
        } else {
            throw new ApiRequestException(responseWrapper);
        }
    }

    /**
     * Default implementation for object response deserialization.
     *
     * @param mapper     json mapper for response conversion
     * @param resultNode deserialized JSON response
     * @param resultType return object Class
     * @param <K>        type of returned object
     * @return converted response with correct type
     */
    default <K extends T> T deserializeObjectResponse(ObjectMapper mapper, JsonNode resultNode, Class<K> resultType) {
        return mapper.convertValue(resultNode, resultType);
    }

    /**
     * Default implementation for collection response deserialization.
     *
     * @param mapper     json mapper for response conversion
     * @param resultNode deserialized JSON response
     * @param resultType return object Class
     * @param <K>        type of returned object
     * @return collection of response objects with correct type
     */
    default <K> T deserializeCollectionResponse(ObjectMapper mapper, JsonNode resultNode, Class<K> resultType) {
        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, resultType);
        return mapper.convertValue(resultNode, collectionType);
    }

    /**
     * Method for validating the properties of the method being sent.
     * <p>
     * In addition to running the standard {@link Validated#validate()} method,
     * the validation method is also launched on any property
     * whose type implements the {@link Validated} interface.
     * <p>
     * It also checks the values of properties
     * that are annotated with the {@link Required} annotation.
     * None of the required properties can be null.
     */
    default void validateProperties() {
        for (Field field : getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object fieldValue;
            try {
                fieldValue = field.get(this);
            } catch (IllegalAccessException e) {
                throw new ApiValidationException(e.getLocalizedMessage(), this);
            }
            if (field.isAnnotationPresent(Required.class)) {
                Required requiredAnnotation = field.getAnnotation(Required.class);
                if (fieldValue == null) {
                    throw new ApiValidationException(field.getName() + " parameter can't be null", this);
                }
                if (!requiredAnnotation.useConstraints()) {
                    if (field.getType() == String.class) {
                        String value = (String) fieldValue;
                        if (value.isEmpty()) {
                            throw new ApiValidationException(field.getName() + " parameter can't be empty", this);
                        }
                    }
                } else {
                    long value = getValue(field, requiredAnnotation, fieldValue);
                    if (value < requiredAnnotation.min() || value > requiredAnnotation.max()) {
                        throw new ApiValidationException(
                                String.format("%s parameter value/length must be in [%d..%d] range",
                                        field.getName(), requiredAnnotation.min(), requiredAnnotation.min()),
                                this);
                    }
                }
            }
            if (Validated.class.isAssignableFrom(field.getType())) {
                ((Validated) fieldValue).validate();
            }
        }
        validate();
    }

    private static long getValue(Field field, Required requiredAnnotation, Object fieldValue) {
        long value = requiredAnnotation.min();
        if (field.getType() == String.class) {
            value = ((String) fieldValue).length();
        } else if (field.getType() == Integer.class) {
            value = (int) fieldValue;
        } else if (field.getType() == Long.class) {
            value = (long) fieldValue;
        } else if (field.getType() == List.class) {
            value = ((List<?>) fieldValue).size();
        }
        return value;
    }
}
