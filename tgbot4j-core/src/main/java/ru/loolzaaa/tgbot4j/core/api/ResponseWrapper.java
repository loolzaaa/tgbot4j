package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.types.ResponseParameters;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWrapper {
    /**
     * This field indicates the success of the request
     */
    @JsonProperty("ok")
    private Boolean ok;

    /**
     * Optional. Human-readable description of the result
     */
    @JsonProperty("description")
    private String description;

    /**
     * Optional. Additional error code if unsuccessful request.
     *
     * @apiNote Its contents are subject to change in the future
     */
    @JsonProperty("errorCode")
    private Integer errorCode;

    /**
     * Optional. Some errors may have this field,
     * that can help to automatically handle the error.
     */
    @JsonProperty("parameters")
    private ResponseParameters parameters;

    /**
     * Result of the query
     */
    @JsonProperty("result")
    private JsonNode result;
}
