package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.types.ResponseParameters;

/**
 * Response API object.
 * <p>
 * Always has a Boolean field 'ok' and may have an optional
 * String field 'description' with a human-readable
 * description of the result.
 * <p>
 * If 'ok' equals True, the request was successful
 * and the result of the query can be found in the 'result' field.
 * <p>
 * In case of an unsuccessful request, 'ok' equals false
 * and the error is explained in the 'description'.
 * <p>
 * An Integer 'error_code' field is also returned,
 * but its contents are subject to change in the future.
 * <p>
 * Some errors may also have an optional field 'parameters'
 * of the type {@link ResponseParameters}, which can help
 * to automatically handle the error.
 */

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
    @JsonProperty("error_code")
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
