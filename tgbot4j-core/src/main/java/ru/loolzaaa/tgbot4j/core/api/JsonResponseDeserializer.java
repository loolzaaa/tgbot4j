package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.ArrayList;

public interface JsonResponseDeserializer<T> {

    T determineResponseType(ObjectMapper mapper, JsonNode resultNode);

    default T deserializeResponse(ObjectMapper mapper, String response) {
        try {
            ResponseWrapper responseWrapper = mapper.readValue(response, ResponseWrapper.class);
            if (responseWrapper.getOk()) {
                return determineResponseType(mapper, responseWrapper.getResult());
            } else {
                //TODO: change to library exception
                throw new RuntimeException(String.format("Error executing %s query", this.getClass().getName()));
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
