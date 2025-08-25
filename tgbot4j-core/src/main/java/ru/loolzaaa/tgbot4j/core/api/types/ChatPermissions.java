package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes actions that a non-administrator user
 * is allowed to take in a chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatPermissions {
    /**
     * Optional. True, if the user is allowed to send text messages,
     * contacts, invoices, locations and venues
     */
    @JsonProperty("can_send_messages")
    private Boolean canSendMessages;

    /**
     * Optional. True, if the user is allowed to send audios
     */
    @JsonProperty("can_send_audios")
    private Boolean canSendAudios;

    /**
     * Optional. True, if the user is allowed to send documents
     */
    @JsonProperty("can_send_documents")
    private Boolean canSendDocuments;

    /**
     * Optional. True, if the user is allowed to send photos
     */
    @JsonProperty("can_send_photos")
    private Boolean canSendPhotos;

    /**
     * Optional. True, if the user is allowed to send videos
     */
    @JsonProperty("can_send_videos")
    private Boolean canSendVideos;

    /**
     * Optional. True, if the user is allowed to send video notes
     */
    @JsonProperty("can_send_video_notes")
    private Boolean canSendVideoNotes;

    /**
     * Optional. True, if the user is allowed to send voice notes
     */
    @JsonProperty("can_send_voice_notes")
    private Boolean canSendVoiceNotes;

    /**
     * Optional. True, if the user is allowed to send polls and checklists
     */
    @JsonProperty("can_send_polls")
    private Boolean canSendPolls;

    /**
     * Optional. True, if the user is allowed to send animations,
     * games, stickers and use inline bots
     */
    @JsonProperty("can_send_other_messages")
    private Boolean canSendOtherMessages;

    /**
     * Optional. True, if the user is allowed to add
     * web page previews to their messages
     */
    @JsonProperty("can_add_web_page_previews")
    private Boolean canAddWebPagePreviews;

    /**
     * Optional. True, if the user is allowed to change the chat title,
     * photo and other settings. Ignored in public supergroups
     */
    @JsonProperty("can_change_info")
    private Boolean canChangeInfo;

    /**
     * Optional. True, if the user is allowed
     * to invite new users to the chat
     */
    @JsonProperty("can_invite_users")
    private Boolean canInviteUsers;

    /**
     * Optional. True, if the user is allowed to pin messages.
     * Ignored in public supergroups
     */
    @JsonProperty("can_pin_messages")
    private Boolean canPinMessages;

    /**
     * Optional. True, if the user is allowed to create forum topics.
     * If omitted defaults to the value of can_pin_messages
     */
    @JsonProperty("can_manage_topics")
    private Boolean canManageTopics;
}
