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
 * Use this method to ban a user in a group, a supergroup or a channel.
 * In the case of supergroups and channels, the user will not be able
 * to return to the chat on their own using invite links, etc., unless {@link UnbanChatMember} first.
 * The bot must be an administrator in the chat for this to work
 * and must have the appropriate administrator rights.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BanChatMember implements TelegramMethod<Boolean> {
    /**
     * Unique identifier for the target group or username
     * of the target supergroup or channel (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Unique identifier of the target user
     */
    @Required
    @JsonProperty("user_id")
    private Integer userId;

    /**
     * Date when the user will be unbanned; Unix time.
     * If user is banned for more than 366 days or less than 30 seconds
     * from the current time they are considered to be banned forever.
     * Applied for supergroups and channels only.
     */
    @JsonProperty("until_date")
    private Integer untilDate;

    /**
     * Pass True to delete all messages from the chat for the user that is being removed.
     * If False, the user will be able to see messages in the group that were sent before the user was removed.
     * Always True for supergroups and channels.
     */
    @JsonProperty("revoke_messages")
    private Boolean revokeMessages;

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
