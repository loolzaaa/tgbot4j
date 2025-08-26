package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a {@link  ChatMember} that is under certain
 * restrictions in the chat. Supergroups only.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMemberRestricted implements ChatMember {
    /**
     * The member's status in the chat, always “restricted”
     */
    @JsonProperty("status")
    private String status;

    /**
     * Information about the user
     */
    @JsonProperty("user")
    private User user;

    /**
     * True, if the user is a member of the chat
     * at the moment of the request
     */
    @JsonProperty("is_member")
    private Boolean isMember;

    /**
     * True, if the user is allowed to send text messages,
     * contacts, invoices, locations and venues
     */
    @JsonProperty("can_send_messages")
    private Boolean canSendMessages;

    /**
     * True, if the user is allowed to send audios
     */
    @JsonProperty("can_send_audios")
    private Boolean canSendAudios;

    /**
     * True, if the user is allowed to send documents
     */
    @JsonProperty("can_send_documents")
    private Boolean canSendDocuments;

    /**
     * True, if the user is allowed to send photos
     */
    @JsonProperty("can_send_photos")
    private Boolean canSendPhotos;

    /**
     * True, if the user is allowed to send videos
     */
    @JsonProperty("can_send_videos")
    private Boolean canSendVideos;

    /**
     * True, if the user is allowed to send video notes
     */
    @JsonProperty("can_send_video_notes")
    private Boolean canSendVideoNotes;

    /**
     * True, if the user is allowed to send voice notes
     */
    @JsonProperty("can_send_voice_notes")
    private Boolean canSendVoiceNotes;

    /**
     * True, if the user is allowed to send polls and checklists
     */
    @JsonProperty("can_send_polls")
    private Boolean canSendPolls;

    /**
     * True, if the user is allowed to send animations,
     * games, stickers and use inline bots
     */
    @JsonProperty("can_send_other_messages")
    private Boolean canSendOtherMessages;

    /**
     * True, if the user is allowed to add web page previews
     * to their messages
     */
    @JsonProperty("can_add_web_page_previews")
    private Boolean canAddWebPagePreviews;

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
     * True, if the user is allowed to pin messages
     */
    @JsonProperty("can_pin_messages")
    private Boolean canPinMessages;

    /**
     * True, if the user is allowed to create forum topics
     */
    @JsonProperty("can_manage_topics")
    private Boolean canManageTopics;

    /**
     * Date when restrictions will be lifted for this user;
     * Unix time. If 0, then the user is restricted forever
     */
    @JsonProperty("until_date")
    private Integer untilDate;
}
