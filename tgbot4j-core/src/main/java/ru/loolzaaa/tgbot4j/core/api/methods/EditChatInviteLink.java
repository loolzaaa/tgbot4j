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
 * Use this method to edit a non-primary invite link created by the bot.
 * The bot must be an administrator in the chat for this to work
 * and must have the appropriate administrator rights.
 * Returns the edited invite link as a {@link ChatInviteLink} object.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditChatInviteLink implements TelegramMethod<ChatInviteLink> {
    /**
     * Unique identifier for the target group or username
     * of the target supergroup or channel (in the format {@code @channelusername})
     */
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * The invite link to edit
     */
    @JsonProperty("invite_link")
    private String inviteLink;

    /**
     * Invite link name; 0-32 characters
     */
    @JsonProperty("name")
    private String name;

    /**
     * 	Point in time (Unix timestamp) when the link will expire
     */
    @JsonProperty("expire_date")
    private Integer expireDate;

    /**
     * The maximum number of users that can be members of the chat
     * simultaneously after joining the chat via this invite link; 1-99999
     */
    @JsonProperty("member_limit")
    private Integer memberLimit;

    /**
     * True, if users joining the chat via the link need
     * to be approved by chat administrators. If True, member_limit can't be specified
     */
    @JsonProperty("creates_join_request")
    private Boolean createsJoinRequest;

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
        if (memberLimit != null && (memberLimit < 1 || memberLimit > 99999)) {
            throw new ApiValidationException("MemberLimit parameter should be from 1 to 99999", this);
        }
        if (createsJoinRequest && memberLimit != null) {
            throw new ApiValidationException("MemberLimit parameter can't be specified if createJoinRequest is true", this);
        }
    }
}
