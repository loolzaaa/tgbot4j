package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import ru.loolzaaa.tgbot4j.core.exception.ApiRequestException;

import java.util.ArrayList;

public interface TelegramMethod<T> extends Validated {

    T determineResponseType(ObjectMapper mapper, JsonNode resultNode);

    default T deserializeResponse(ObjectMapper mapper, String response) {
        //TODO: check stack trace for determineResponseType method, prevent stack overflow
        try {
            ResponseWrapper responseWrapper = mapper.readValue(response, ResponseWrapper.class);
            if (responseWrapper.getOk()) {
                return determineResponseType(mapper, responseWrapper.getResult());
            } else {
                throw new ApiRequestException(responseWrapper);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    default <K extends T> T deserializeObjectResponse(ObjectMapper mapper, JsonNode resultNode, Class<K> resultType) {
        return mapper.convertValue(resultNode, resultType);
    }

    default <K> T deserializeCollectionResponse(ObjectMapper mapper, JsonNode resultNode, Class<K> resultType) {
        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, resultType);
        return mapper.convertValue(resultNode, collectionType);
    }
}
