package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.InlineQueryResult;
import ru.loolzaaa.tgbot4j.core.api.types.SentWebAppMessage;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to set the result of an interaction with
 * a <a href="https://core.telegram.org/bots/webapps">Web App</a>
 * and send a corresponding message on behalf of the user
 * to the chat from which the query originated.
 * On success, a {@link SentWebAppMessage} object is returned.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerWebAppQuery implements TelegramMethod<SentWebAppMessage> {
    /**
     * Unique identifier for the query to be answered
     */
    @JsonProperty("web_app_query_id")
    private String webAppQueryId;

    /**
     * A JSON-serialized object describing the message to be sent
     */
    @JsonProperty("result")
    private InlineQueryResult result;

    @Override
    public SentWebAppMessage determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, SentWebAppMessage.class);
    }

    @Override
    public void validate() {
        if (webAppQueryId == null || webAppQueryId.isEmpty()) {
            throw new ApiValidationException("WebAppQueryId parameter can't be null or empty", this);
        }
        if (result == null) {
            throw new ApiValidationException("InlineQueryResult parameter can't be null", this);
        }
    }
}
