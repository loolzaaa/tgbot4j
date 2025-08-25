package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.InlineQueryResult;
import ru.loolzaaa.tgbot4j.core.api.types.PreparedInlineMessage;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Allows the bot to cancel or re-enable extension
 * of a subscription paid in Telegram Stars.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavePreparedInlineMessage implements TelegramMethod<PreparedInlineMessage> {
    /**
     * Unique identifier of the target user that can use the prepared message
     */
    @Required
    @JsonProperty("user_id")
    private Integer userId;

    /**
     * A JSON-serialized object describing the message to be sent
     */
    @Required
    @JsonProperty("result")
    private InlineQueryResult result;

    /**
     * Pass True if the message can be sent to private chats with users
     */
    @JsonProperty("allow_user_chats")
    private Boolean allowUserChats;

    /**
     * Pass True if the message can be sent to private chats with bots
     */
    @JsonProperty("allow_bot_chats")
    private Boolean allowBotChats;

    /**
     * Pass True if the message can be sent to group and supergroup chats
     */
    @JsonProperty("allow_group_chats")
    private Boolean allowGroupChats;

    /**
     * Pass True if the message can be sent to channel chats
     */
    @JsonProperty("allow_channel_chats")
    private Boolean allowChannelChats;

    @Override
    public PreparedInlineMessage determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, PreparedInlineMessage.class);
    }

    @Override
    public void validate() {
        if (userId == null) {
            throw new ApiValidationException("User ID parameter can't be null", this);
        }
        if (result == null) {
            throw new ApiValidationException("Result parameter can't be null", this);
        }
    }
}
