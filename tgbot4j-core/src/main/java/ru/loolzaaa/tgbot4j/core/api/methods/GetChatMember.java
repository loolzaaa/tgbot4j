package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.ChatMember;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to get information about a member of a chat.
 * The method is only guaranteed to work for other users
 * if the bot is an administrator in the chat.
 * Returns a ChatMember object on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetChatMember implements TelegramMethod<ChatMember> {
    /**
     * Unique identifier for the target group or username
     * of the target supergroup or channel (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * 	Unique identifier of the target user
     */
    @Required
    @JsonProperty("user_id")
    private Integer userId;

    @Override
    public ChatMember determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, ChatMember.class);
    }

    @Override
    public void validate() {
        if (chatId == null) {
            throw new ApiValidationException("Chat ID parameter can't be null", this);
        }
        if (userId == null) {
            throw new ApiValidationException("User ID parameter can't be null", this);
        }
    }
}
