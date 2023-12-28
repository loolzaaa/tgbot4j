package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.ChatInviteLink;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to revoke an invite link created by the bot.
 * If the primary link is revoked, a new link is automatically generated.
 * The bot must be an administrator in the chat for this to work
 * and must have the appropriate administrator rights.
 * Returns the revoked invite link as {@link ChatInviteLink} object.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevokeChatInviteLink implements TelegramMethod<ChatInviteLink> {
    /**
     * Unique identifier for the target group or username
     * of the target supergroup or channel (in the format {@code @channelusername})
     */
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * The invite link to revoke
     */
    @JsonProperty("invite_link")
    private String inviteLink;

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
    }
}
