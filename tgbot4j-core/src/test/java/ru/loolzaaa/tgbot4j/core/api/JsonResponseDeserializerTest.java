package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonResponseDeserializerTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void shouldCorrectDeserializeConcreteType() {
        final String json = "{\"ok\": true, \"result\":{\"name\": \"Scott\", \"value\": 1111}}";

        TestResponse testResponse = new ConcreteTypeResponse().deserializeResponse(mapper, json);

        assertEquals("Scott", testResponse.getName());
        assertEquals(1111, testResponse.getValue());
    }

    @Test
    void shouldCorrectDeserializeObjectType() {
        final String json = "{\"ok\": true, \"result\": true}";

        boolean result = (boolean) new ObjectResponse().deserializeResponse(mapper, json);

        assertTrue(result);
    }

    @Test
    void shouldCorrectDeserializeCollectionType() {
        final String json = "{\"ok\": true, \"result\":[{\"name\": \"Scott\", \"value\": 1111}, {\"name\": \"Mary\", \"value\": 2222}]}";

        List<TestResponse> testResponses = new CollectionResponse().deserializeResponse(mapper, json);

        assertEquals(2, testResponses.size());
        assertEquals("Scott", testResponses.get(0).getName());
        assertEquals(1111, testResponses.get(0).getValue());
        assertEquals("Mary", testResponses.get(1).getName());
        assertEquals(2222, testResponses.get(1).getValue());
    }

    @Test
    void shouldThrowExceptionIfRequestNotSuccess() {
        final String json = "{\"ok\": false, \"description\": \"test error\"}";

        assertThrows(RuntimeException.class, () -> new ConcreteTypeResponse().deserializeResponse(mapper, json));
    }

    static class ConcreteTypeResponse implements JsonResponseDeserializer<TestResponse> {
        @Override
        public TestResponse determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
            return  deserializeObjectResponse(mapper, resultNode, TestResponse.class);
        }
    }

    static class ObjectResponse implements JsonResponseDeserializer<Object> {
        @Override
        public Object determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
            if (resultNode instanceof ObjectNode) {
                return deserializeObjectResponse(mapper, resultNode, TestResponse.class);
            } else if (resultNode instanceof BooleanNode){
                return deserializeObjectResponse(mapper, resultNode, Boolean.class);
            }
            return null;
        }
    }
    static class CollectionResponse implements JsonResponseDeserializer<List<TestResponse>> {
        @Override
        public List<TestResponse> determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
            return deserializeCollectionResponse(mapper, resultNode, TestResponse.class);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class TestResponse {
        String name;
        Integer value;
    }
}