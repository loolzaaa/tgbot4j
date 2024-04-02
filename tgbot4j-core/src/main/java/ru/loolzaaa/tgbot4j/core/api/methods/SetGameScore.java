package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.Message;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to set the score of the specified user in a game message.
 * On success, if the message is not an inline message,
 * the {@link Message} is returned, otherwise True is returned.
 * Returns an error, if the new score is not greater
 * than the user's current score in the chat and force is False.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetGameScore implements TelegramMethod<Object> {
    /**
     * User identifier
     */
    @Required
    @JsonProperty("user_id")
    private Long userId;

    /**
     * New score, must be non-negative
     */
    @Required
    @JsonProperty("score")
    private Integer score;

    /**
     * Pass True if the high score is allowed to decrease.
     * This can be useful when fixing mistakes or banning cheaters
     */
    @JsonProperty("force")
    private Boolean force;

    /**
     * Pass True if the game message should not be automatically
     * edited to include the current scoreboard
     */
    @JsonProperty("disable_edit_message")
    private Boolean disableEditMessage;

    /**
     * Required if inline_message_id is not specified.
     * Unique identifier for the target chat
     */
    @JsonProperty("chat_id")
    private Integer chatId;

    /**
     * Required if inline_message_id is not specified.
     * Identifier of the sent message
     */
    @JsonProperty("message_id")
    private Integer messageId;

    /**
     * 	Required if chat_id and message_id are not specified.
     * 	Identifier of the inline message
     */
    @JsonProperty("inline_message_id")
    private String inlineMessageId;

    @Override
    public Object determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        if (resultNode instanceof ObjectNode) {
            return deserializeObjectResponse(mapper, resultNode, Message.class);
        } else if (resultNode instanceof BooleanNode){
            return deserializeObjectResponse(mapper, resultNode, Boolean.class);
        }
        return null;
    }

    @Override
    public void validate() {
        if (userId == null) {
            throw new ApiValidationException("User ID parameter can't be null", this);
        }
        if (score == null || score < 0) {
            throw new ApiValidationException("Score parameter can't be null or negative", this);
        }
        if (chatId != null && (inlineMessageId != null && !inlineMessageId.isEmpty())) {
            throw new ApiValidationException("Chat ID parameter can't be specified if InlineMessageId parameter is specified", this);
        }
        if (messageId != null && (inlineMessageId != null && !inlineMessageId.isEmpty())) {
            throw new ApiValidationException("Message ID parameter can't be specified if InlineMessageId parameter is specified", this);
        }
    }
}
