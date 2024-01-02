package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.UserChatBoosts;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to get the list of boosts added
 * to a chat by a user. Requires administrator rights
 * in the chat. Returns a {@link UserChatBoosts} object.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserChatBoosts implements TelegramMethod<UserChatBoosts> {
    /**
     * Unique identifier for the chat or username
     * of the channel (in the format {@code @channelusername})
     */
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Unique identifier of the target user
     */
    @JsonProperty("user_id")
    private Long userId;

    @Override
    public UserChatBoosts determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, UserChatBoosts.class);
    }

    @Override
    public void validate() {
        if (chatId == null || chatId.isEmpty()) {
            throw new ApiValidationException("Chat ID parameter can't be null or empty", this);
        }
        if (userId == null) {
            throw new ApiValidationException("User ID parameter can't be null", this);
        }
    }
}
