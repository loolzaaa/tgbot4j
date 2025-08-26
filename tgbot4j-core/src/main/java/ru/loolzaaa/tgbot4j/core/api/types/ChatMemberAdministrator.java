package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a {@link ChatMember} that has some additional privileges.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMemberAdministrator implements ChatMember {
    /**
     * The member's status in the chat, always “administrator”
     */
    @JsonProperty("status")
    private String status;

    /**
     * 	Information about the user
     */
    @JsonProperty("user")
    private User user;

    /**
     * True, if the bot is allowed to edit administrator
     * privileges of that user
     */
    @JsonProperty("can_be_edited")
    private Boolean canBeEdited;

    /**
     * True, if the user's presence in the chat is hidden
     */
    @JsonProperty("is_anonymous")
    private Boolean isAnonymous;

    /**
     * True, if the administrator can access the chat event log,
     * boost list in channels, see channel members, report spam messages,
     * see anonymous administrators in supergroups and ignore slow mode,
     * and send messages to the chat without paying Telegram Stars.
     * Implied by any other administrator privilege
     */
    @JsonProperty("can_manage_chat")
    private Boolean canManageChat;

    /**
     * True, if the administrator can delete messages of other users
     */
    @JsonProperty("can_delete_messages")
    private Boolean canDeleteMessages;

    /**
     * True, if the administrator can manage video chats
     */
    @JsonProperty("can_manage_video_chats")
    private Boolean canManageVideoChats;

    /**
     * True, if the administrator can restrict,
     * ban or unban chat members, or access supergroup statistics
     */
    @JsonProperty("can_restrict_members")
    private Boolean canRestrictMembers;

    /**
     * True, if the administrator can add new administrators
     * with a subset of their own privileges or demote administrators
     * that they have promoted, directly or indirectly
     * (promoted by administrators that were appointed by the user)
     */
    @JsonProperty("can_promote_members")
    private Boolean canPromoteMembers;

    /**
     * True, if the user is allowed to change the chat title,
     * photo and other settings
     */
    @JsonProperty("can_change_info")
    private Boolean canChangeInfo;

    /**
     * True, if the user is allowed to invite new users to the chat
     */
    @JsonProperty("can_invite_users")
    private Boolean canInviteUsers;

    /**
     * Optional. True, if the administrator can post messages
     * in the channel, approve suggested posts,
     * or access channel statistics; channels only
     */
    @JsonProperty("can_post_messages")
    private Boolean canPostMessages;

    /**
     * Optional. True, if the administrator can edit messages
     * of other users and can pin messages; channels only
     */
    @JsonProperty("can_edit_messages")
    private Boolean canEditMessages;

    /**
     * Optional. True, if the user is allowed to pin messages;
     * groups and supergroups only
     */
    @JsonProperty("can_pin_messages")
    private Boolean canPinMessages;

    /**
     * Optional. True, if the administrator can post stories
     * in the channel; channels only
     */
    @JsonProperty("can_post_stories")
    private Boolean canPostStories;

    /**
     * Optional. True, if the administrator can edit stories
     * posted by other users, post stories to the chat page,
     * pin chat stories, and access the chat's story archive; channels only
     */
    @JsonProperty("can_edit_stories")
    private Boolean canEditStories;

    /**
     * Optional. True, if the administrator can delete stories
     * posted by other users; channels only
     */
    @JsonProperty("can_delete_stories")
    private Boolean canDeleteStories;

    /**
     * Optional. True, if the user is allowed to create, rename,
     * close, and reopen forum topics; supergroups only
     */
    @JsonProperty("can_manage_topics")
    private Boolean canManageTopics;

    /**
     * Optional. True, if the administrator can manage
     * direct messages of the channel and decline suggested posts;
     * for channels only
     */
    @JsonProperty("can_manage_direct_messages")
    private Boolean canManageDirectMessages;

    /**
     * Optional. Custom title for this user
     */
    @JsonProperty("custom_title")
    private String customTitle;
}
