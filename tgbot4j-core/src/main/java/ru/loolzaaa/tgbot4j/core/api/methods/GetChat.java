package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.Chat;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to get up to date information
 * about the chat (current name of the user for one-on-one conversations,
 * current username of a user, group or channel, etc.).
 * Returns a {@link Chat} object on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetChat implements TelegramMethod<Chat> {
    /**
     * Unique identifier for the target chat or username
     * of the target supergroup or channel (in the format {@code @channelusername})
     */
    @JsonProperty("chat_id")
    private String chatId;

    @Override
    public Chat determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Chat.class);
    }

    @Override
    public void validate() {
        if (chatId == null || chatId.isEmpty()) {
            throw new ApiValidationException("Chat ID parameter can't be null or empty", this);
        }
    }
}
