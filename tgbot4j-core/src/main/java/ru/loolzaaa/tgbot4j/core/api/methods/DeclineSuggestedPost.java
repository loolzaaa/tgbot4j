package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to decline a suggested post
 * in a direct messages chat. The bot must have
 * the 'can_manage_direct_messages' administrator right
 * in the corresponding channel chat.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeclineSuggestedPost implements TelegramMethod<Boolean> {
    /**
     * Unique identifier for the target direct messages chat
     */
    @Required
    @JsonProperty("chat_id")
    private Integer chatId;

    /**
     * Identifier of a suggested post message to approve
     */
    @Required
    @JsonProperty("message_id")
    private Integer messageId;

    /**
     * Comment for the creator of the suggested post;
     * 0-128 characters
     */
    @JsonProperty("comment")
    private String comment;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (chatId == null) {
            throw new ApiValidationException("Chat ID parameter can't be null", this);
        }
        if (messageId == null) {
            throw new ApiValidationException("Message Id parameter can't be null", this);
        }
        if (comment != null && comment.length() > 128) {
            throw new ApiValidationException("Comment parameter must not exceed 128", this);
        }
    }
}
