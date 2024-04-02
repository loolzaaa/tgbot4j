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
 * Use this method to ban a channel chat in a supergroup or a channel.
 * Until the chat is unbanned, the owner of the banned chat won't be able
 * to send messages on behalf of any of their channels.
 * The bot must be an administrator in the supergroup
 * or channel for this to work and must have the appropriate administrator rights.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BanChatSenderChat implements TelegramMethod<Boolean> {
    /**
     * Unique identifier for the target group or username
     * of the target supergroup or channel (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Unique identifier of the target sender chat
     */
    @Required
    @JsonProperty("sender_chat_id")
    private Integer senderChatId;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (chatId == null) {
            throw new ApiValidationException("Chat ID parameter can't be null", this);
        }
        if (senderChatId == null) {
            throw new ApiValidationException("SenderChatId parameter can't be null", this);
        }
    }
}
