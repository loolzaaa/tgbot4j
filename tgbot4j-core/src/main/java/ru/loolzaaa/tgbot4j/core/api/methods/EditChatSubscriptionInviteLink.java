package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.ChatInviteLink;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to edit a subscription invite link created by the bot.
 * The bot must have the can_invite_users administrator rights.
 * Returns the new invite link as a {@link ChatInviteLink} object.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditChatSubscriptionInviteLink implements TelegramMethod<ChatInviteLink> {
    /**
     * Unique identifier for the target group or username
     * of the target supergroup or channel (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * The invite link to edit
     */
    @Required
    @JsonProperty("invite_link")
    private String inviteLink;

    /**
     * Invite link name; 0-32 characters
     */
    @JsonProperty("name")
    private String name;

    @Override
    public ChatInviteLink determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, ChatInviteLink.class);
    }

    @Override
    public void validate() {
        if (chatId == null) {
            throw new ApiValidationException("Chat ID parameter can't be null", this);
        }
        if (inviteLink == null) {
            throw new ApiValidationException("InviteLink parameter can't be null", this);
        }
        if (name != null && name.length() > 32) {
            throw new ApiValidationException("Name parameter can't be null", this);
        }
    }
}
