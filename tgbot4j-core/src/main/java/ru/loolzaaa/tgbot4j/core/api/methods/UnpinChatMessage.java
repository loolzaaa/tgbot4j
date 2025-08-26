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
 * Use this method to remove a message from the list
 * of pinned messages in a chat.
 * In private chats and channel direct messages chats,
 * all messages can be unpinned. Conversely, the bot
 * must be an administrator with the 'can_pin_messages' right
 * or the 'can_edit_messages' right to unpin messages
 * in groups and channels respectively.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnpinChatMessage implements TelegramMethod<Boolean> {
    /**
     * Unique identifier of the business connection
     * on behalf of which the message will be unpinned
     */
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    /**
     * Unique identifier for the target group or username
     * of the target supergroup or channel (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Identifier of the message to unpin.
     * Required if <i>business_connection_id</i> is specified.
     * If not specified, the most recent pinned message
     * (by sending date) will be unpinned.
     */
    @JsonProperty("message_id")
    private Integer messageId;

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
            throw new ApiValidationException("Message ID parameter can't be null", this);
        }
    }
}
