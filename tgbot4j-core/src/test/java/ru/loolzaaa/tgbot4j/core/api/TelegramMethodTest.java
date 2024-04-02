package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TelegramMethodTest {

    ObjectMapper mapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Test
    void shouldCorrectDeserializeConcreteType() throws IOException {
        final String json = "{\"ok\": true, \"result\":{\"name\": \"Scott\", \"value\": 1111}}";

        TestResponse testResponse = new ConcreteTypeResponse().deserializeResponse(mapper, json);

        assertEquals("Scott", testResponse.getName());
        assertEquals(1111, testResponse.getValue());
    }

    @Test
    void shouldCorrectDeserializeObjectType() throws IOException {
        final String json = "{\"ok\": true, \"result\": true}";

        boolean result = (boolean) new ObjectResponse().deserializeResponse(mapper, json);

        assertTrue(result);
    }

    @Test
    void shouldCorrectDeserializeCollectionType() throws IOException {
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

    @Test
    void shouldThrowExceptionWhileMethodValidationWithoutConstraints() {
        ConcreteTypeResponse concreteTypeResponse = new ConcreteTypeResponse();

        concreteTypeResponse.stringValue = "TEST";
        concreteTypeResponse.intValue = 1;
        concreteTypeResponse.longValue = 1L;
        concreteTypeResponse.integerListValue = List.of(1);
        concreteTypeResponse.testObject = new TestObject(-200); // WRONG
        assertThrows(ApiValidationException.class, concreteTypeResponse::validateProperties);

        concreteTypeResponse.stringValue = "TEST";
        concreteTypeResponse.intValue = 1;
        concreteTypeResponse.longValue = 1L;
        concreteTypeResponse.integerListValue = List.of(1);
        concreteTypeResponse.testObject = new TestObject(200); // WRONG
        assertThrows(ApiValidationException.class, concreteTypeResponse::validateProperties);

        concreteTypeResponse.stringValue = ""; // WRONG
        concreteTypeResponse.intValue = 1;
        concreteTypeResponse.longValue = 1L;
        concreteTypeResponse.integerListValue = List.of(1);
        concreteTypeResponse.testObject = new TestObject(100);
        assertThrows(ApiValidationException.class, concreteTypeResponse::validateProperties);

        concreteTypeResponse.stringValue = "TEST";
        concreteTypeResponse.intValue = null; // WRONG
        concreteTypeResponse.longValue = 1L;
        concreteTypeResponse.integerListValue = List.of(1);
        concreteTypeResponse.testObject = new TestObject(100);
        assertThrows(ApiValidationException.class, concreteTypeResponse::validateProperties);

        concreteTypeResponse.stringValue = "TEST";
        concreteTypeResponse.intValue = 1;
        concreteTypeResponse.longValue = null; // WRONG
        concreteTypeResponse.integerListValue = List.of(1);
        concreteTypeResponse.testObject = new TestObject(100);
        assertThrows(ApiValidationException.class, concreteTypeResponse::validateProperties);

        concreteTypeResponse.stringValue = "TEST";
        concreteTypeResponse.intValue = 1;
        concreteTypeResponse.longValue = 1L;
        concreteTypeResponse.integerListValue = null; // WRONG
        concreteTypeResponse.testObject = new TestObject(100);
        assertThrows(ApiValidationException.class, concreteTypeResponse::validateProperties);
    }

    @Test
    void shouldThrowExceptionWhileMethodValidationWithConstraints() {
        ConcreteTypeConstraintResponse concreteTypeResponse = new ConcreteTypeConstraintResponse();

        concreteTypeResponse.stringValue = "A"; // WRONG
        concreteTypeResponse.intValue = 3;
        concreteTypeResponse.longValue = 3L;
        concreteTypeResponse.integerListValue = List.of(1, 2, 3);
        assertThrows(ApiValidationException.class, concreteTypeResponse::validateProperties);

        concreteTypeResponse.stringValue = "AAAAA"; // WRONG
        concreteTypeResponse.intValue = 3;
        concreteTypeResponse.longValue = 3L;
        concreteTypeResponse.integerListValue = List.of(1, 2, 3);
        assertThrows(ApiValidationException.class, concreteTypeResponse::validateProperties);

        concreteTypeResponse.stringValue = "AAA";
        concreteTypeResponse.intValue = 1; // WRONG
        concreteTypeResponse.longValue = 3L;
        concreteTypeResponse.integerListValue = List.of(1, 2, 3);
        assertThrows(ApiValidationException.class, concreteTypeResponse::validateProperties);

        concreteTypeResponse.stringValue = "AAA";
        concreteTypeResponse.intValue = 5; // WRONG
        concreteTypeResponse.longValue = 3L;
        concreteTypeResponse.integerListValue = List.of(1, 2, 3);
        assertThrows(ApiValidationException.class, concreteTypeResponse::validateProperties);

        concreteTypeResponse.stringValue = "AAA";
        concreteTypeResponse.intValue = 3;
        concreteTypeResponse.longValue = 1L; // WRONG
        concreteTypeResponse.integerListValue = List.of(1, 2, 3);
        assertThrows(ApiValidationException.class, concreteTypeResponse::validateProperties);

        concreteTypeResponse.stringValue = "AAA";
        concreteTypeResponse.intValue = 3;
        concreteTypeResponse.longValue = 5L; // WRONG
        concreteTypeResponse.integerListValue = List.of(1, 2, 3);
        assertThrows(ApiValidationException.class, concreteTypeResponse::validateProperties);

        concreteTypeResponse.stringValue = "AAA";
        concreteTypeResponse.intValue = 3;
        concreteTypeResponse.longValue = 3L;
        concreteTypeResponse.integerListValue = List.of(1); // WRONG
        assertThrows(ApiValidationException.class, concreteTypeResponse::validateProperties);

        concreteTypeResponse.stringValue = "AAA";
        concreteTypeResponse.intValue = 3;
        concreteTypeResponse.longValue = 3L;
        concreteTypeResponse.integerListValue = List.of(1, 2, 3, 4, 5); // WRONG
        assertThrows(ApiValidationException.class, concreteTypeResponse::validateProperties);
    }

    static class ConcreteTypeResponse implements TelegramMethod<TestResponse> {

        @Required
        private String stringValue;
        @Required
        private Integer intValue;
        @Required
        private Long longValue;
        @Required
        private List<Integer> integerListValue;

        private TestObject testObject;

        @Override
        public TestResponse determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
            return  deserializeObjectResponse(mapper, resultNode, TestResponse.class);
        }
    }

    static class ConcreteTypeConstraintResponse implements TelegramMethod<TestResponse> {

        @Required(useConstraints = true, min = 2, max = 4)
        private String stringValue;
        @Required(useConstraints = true, min = 2, max = 4)
        private Integer intValue;
        @Required(useConstraints = true, min = 2, max = 4)
        private Long longValue;
        @Required(useConstraints = true, min = 2, max = 4)
        private List<Integer> integerListValue;

        @Override
        public TestResponse determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
            return  deserializeObjectResponse(mapper, resultNode, TestResponse.class);
        }
    }

    static class ObjectResponse implements TelegramMethod<Object> {
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

    static class CollectionResponse implements TelegramMethod<List<TestResponse>> {
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

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class TestObject implements Validated {

        Integer value;

        @Override
        public void validate() {
            if (value == null || value < 0 || value > 128) {
                throw new ApiValidationException("value must not be null, less than 0 or greater than 128", this);
            }
        }
    }
}