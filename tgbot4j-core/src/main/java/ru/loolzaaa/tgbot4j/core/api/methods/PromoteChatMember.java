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
 * Use this method to promote or demote a user in a supergroup or a channel.
 * The bot must be an administrator in the chat for this to work and must have
 * the appropriate administrator rights.
 * Pass False for all boolean parameters to demote a user.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromoteChatMember implements TelegramMethod<Boolean> {
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
    private Long userId;

    /**
     * Pass True if the administrator's presence in the chat is hidden
     */
    @JsonProperty("is_anonymous")
    private Boolean isAnonymous;

    /**
     * Pass True if the administrator can access the chat event log, boost list in channels,
     * see channel members, report spam messages, see anonymous administrators
     * in supergroups and ignore slow mode.
     * Implied by any other administrator privilege
     */
    @JsonProperty("can_manage_chat")
    private Boolean canManageChat;

    /**
     * Pass True if the administrator can delete messages of other users
     */
    @JsonProperty("can_delete_messages")
    private Boolean canDeleteMessages;

    /**
     * Pass True if the administrator can manage video chats
     */
    @JsonProperty("can_manage_video_chats")
    private Boolean canManageVideoChats;

    /**
     * Pass True if the administrator can restrict, ban or unban chat members,
     * or access supergroup statistics
     */
    @JsonProperty("can_restrict_members")
    private Boolean canRestrictMembers;

    /**
     * Pass True if the administrator can add new administrators
     * with a subset of their own privileges or demote administrators
     * that they have promoted, directly or indirectly
     * (promoted by administrators that were appointed by him)
     */
    @JsonProperty("can_promote_members")
    private Boolean canPromoteMembers;

    /**
     * Pass True if the administrator can change chat title,
     * photo and other settings
     */
    @JsonProperty("can_change_info")
    private Boolean canChangeInfo;

    /**
     * 	Pass True if the administrator can invite new users to the chat
     */
    @JsonProperty("can_invite_users")
    private Boolean canInviteUsers;

    /**
     * Pass True if the administrator can post messages in the channel,
     * or access channel statistics; channels only
     */
    @JsonProperty("can_post_messages")
    private Boolean canPostMessages;

    /**
     * 	Pass True if the administrator can edit messages of other users
     * 	and can pin messages; channels only
     */
    @JsonProperty("can_edit_messages")
    private Boolean canEditMessages;

    /**
     * Pass True if the administrator can pin messages, supergroups only
     */
    @JsonProperty("can_pin_messages")
    private Boolean canPinMessages;

    /**
     * Pass True if the administrator can post stories in the channel;
     * channels only
     */
    @JsonProperty("can_post_stories")
    private Boolean canPostStories;

    /**
     * Pass True if the administrator can edit stories posted by other users;
     * channels only
     */
    @JsonProperty("can_edit_stories")
    private Boolean canEditStories;

    /**
     * 	Pass True if the administrator can delete stories posted by other users;
     * 	channels only
     */
    @JsonProperty("can_delete_stories")
    private Boolean canDeleteStories;

    /**
     * Pass True if the user is allowed to create, rename, close,
     * and reopen forum topics, supergroups only
     */
    @JsonProperty("can_manage_topics")
    private Boolean canManageTopics;

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
