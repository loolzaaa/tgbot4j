package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to unban a previously banned user in a supergroup or channel.
 * The user will <b>not</b> return to the group or channel automatically,
 * but will be able to join via link, etc. The bot must be an administrator for this to work.
 * By default, this method guarantees that after the call the user is not a member of the chat,
 * but will be able to join it. So if the user is a member of the chat
 * they will also be <b>removed</b> from the chat. If you don't want this,
 * use the parameter only_if_banned.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnbanChatMember implements TelegramMethod<Boolean> {
    /**
     * Unique identifier for the target group or username
     * of the target supergroup or channel (in the format {@code @channelusername})
     */
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Unique identifier of the target user
     */
    @JsonProperty("user_id")
    private Integer userId;

    /**
     * Do nothing if the user is not banned
     */
    @JsonProperty("only_if_banned")
    private Boolean onlyIfBanned;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
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
