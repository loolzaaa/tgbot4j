package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object represents a message.
 */

@JsonDeserialize // Prevent stack overflow
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message implements MaybeInaccessibleMessage {
    /**
     * Unique message identifier inside this chat
     */
    @JsonProperty("message_id")
    private Integer messageId;

    /**
     * Optional. Unique identifier of a message thread
     * to which the message belongs; for supergroups only
     */
    @JsonProperty("message_thread_id")
    private Integer messageThreadId;

    /**
     * Optional. Sender of the message; empty for messages
     * sent to channels. For backward compatibility,
     * the field contains a fake sender user in non-channel chats,
     * if the message was sent on behalf of a chat.
     */
    @JsonProperty("from")
    private User from;

    /**
     * Optional. Sender of the message, sent on behalf of a chat.
     * For example, the channel itself for channel posts,
     * the supergroup itself for messages from anonymous group administrators,
     * the linked channel for messages automatically forwarded
     * to the discussion group. For backward compatibility,
     * the field from contains a fake sender user in non-channel chats,
     * if the message was sent on behalf of a chat.
     */
    @JsonProperty("sender_chat")
    private Chat senderChat;

    /**
     * Optional. If the sender of the message boosted the chat,
     * the number of boosts added by the user
     */
    @JsonProperty("sender_boost_count")
    private Integer senderBoostCount;

    /**
     * Date the message was sent in Unix time
     */
    @JsonProperty("date")
    private Integer date;

    /**
     * Conversation the message belongs to
     */
    @JsonProperty("chat")
    private Chat chat;

    /**
     * Optional. Information about the original message
     * for forwarded messages
     */
    @JsonProperty("forward_origin")
    private MessageOrigin forwardOrigin;

    /**
     * Optional. True, if the message is sent to a forum topic
     */
    @JsonProperty("is_topic_message")
    private Boolean isTopicMessage;

    /**
     * Optional. True, if the message is a channel post
     * that was automatically forwarded
     * to the connected discussion group
     */
    @JsonProperty("is_automatic_forward")
    private Boolean isAutomaticForward;

    /**
     * Optional. For replies, the original message.
     * Note that the Message object in this field will
     * not contain further reply_to_message fields
     * even if it itself is a reply.
     */
    @JsonProperty("reply_to_message")
    private Message replyToMessage;

    /**
     * Optional. Information about the message
     * that is being replied to, which may come
     * from another chat or forum topic
     */
    @JsonProperty("external_reply")
    private ExternalReplyInfo externalReply;

    /**
     * Optional. For replies that quote part
     * of the original message, the quoted part
     * of the message
     */
    @JsonProperty("quote")
    private TextQuote quote;

    /**
     * Optional. For replies to a story, the original story
     */
    @JsonProperty("reply_to_story")
    private Story replyToStory;

    /**
     * Optional. Bot through which the message was sent
     */
    @JsonProperty("via_bot")
    private User viaBot;

    /**
     * Optional. Date the message was last edited in Unix time
     */
    @JsonProperty("edit_date")
    private Integer editDate;

    /**
     * Optional. True, if the message can't be forwarded
     */
    @JsonProperty("has_protected_content")
    private Boolean hasProtectedContent;

    /**
     * Optional. The unique identifier of a media message group
     * this message belongs to
     */
    @JsonProperty("media_group_id")
    private String mediaGroupId;

    /**
     * Optional. Signature of the post author for messages
     * in channels, or the custom title
     * of an anonymous group administrator
     */
    @JsonProperty("author_signature")
    private String authorSignature;

    /**
     * Optional. For text messages, the actual UTF-8 text
     * of the message
     */
    @JsonProperty("text")
    private String text;

    /**
     * Optional. For text messages, special entities
     * like usernames, URLs, bot commands, etc. that appear
     * in the text
     */
    @JsonProperty("entities")
    private List<MessageEntity> entities;

    /**
     * Optional. Options used for link preview generation
     * for the message, if it is a text message
     * and link preview options were changed
     */
    @JsonProperty("link_preview_options")
    private LinkPreviewOptions linkPreviewOptions;

    /**
     * Optional. Message is an animation, information about the animation.
     * For backward compatibility, when this field is set,
     * the document field will also be set
     */
    @JsonProperty("animation")
    private Animation animation;

    /**
     * Optional. Message is an audio file, information about the file
     */
    @JsonProperty("audio")
    private Audio audio;

    /**
     * Optional. Message is a general file, information about the file
     */
    @JsonProperty("document")
    private Document document;

    /**
     * Optional. Message is a photo, available sizes of the photo
     */
    @JsonProperty("photo")
    private List<PhotoSize> photo;

    /**
     * Optional. Message is a sticker, information about the sticker
     */
    @JsonProperty("sticker")
    private Sticker sticker;

    /**
     * Optional. Message is a forwarded story
     */
    @JsonProperty("story")
    private Story story;

    /**
     * Optional. Message is a video, information about the video
     */
    @JsonProperty("video")
    private Video video;

    /**
     * Optional. Message is a <a href="https://telegram.org/blog/video-messages-and-telescope">video note</a>,
     * information about the video message
     */
    @JsonProperty("video_note")
    private VideoNote videoNote;

    /**
     * Optional. Message is a voice message, information about the file
     */
    @JsonProperty("voice")
    private Voice voice;

    /**
     * Optional. Caption for the animation, audio, document,
     * photo, video or voice
     */
    @JsonProperty("caption")
    private String caption;

    /**
     * Optional. For messages with a caption, special entities like usernames,
     * URLs, bot commands, etc. that appear in the caption
     */
    @JsonProperty("caption_entities")
    private List<MessageEntity> captionEntities;

    /**
     * Optional. True, if the message media is covered by a spoiler animation
     */
    @JsonProperty("has_media_spoiler")
    private Boolean hasMediaSpoiler;

    /**
     * Optional. Message is a shared contact, information about the contact
     */
    @JsonProperty("contact")
    private Contact contact;

    /**
     * Optional. Message is a dice with random value
     */
    @JsonProperty("dice")
    private Dice dice;

    /**
     * Optional. Message is a game, information about the game.
     * <a href="https://core.telegram.org/bots/api#games">More about games »</a>
     */
    @JsonProperty("game")
    private Game game;

    /**
     * Optional. Message is a native poll, information about the poll
     */
    @JsonProperty("poll")
    private Poll poll;

    /**
     * Optional. Message is a venue, information about the venue.
     * For backward compatibility, when this field is set,
     * the location field will also be set
     */
    @JsonProperty("venue")
    private Venue venue;

    /**
     * Optional. Message is a shared location, information about the location
     */
    @JsonProperty("location")
    private Location location;

    /**
     * Optional. New members that were added to the group
     * or supergroup and information about them
     * (the bot itself may be one of these members)
     */
    @JsonProperty("new_chat_members")
    private List<User> newChatMembers;

    /**
     * Optional. A member was removed from the group,
     * information about them (this member may be the bot itself)
     */
    @JsonProperty("left_chat_member")
    private User leftChatMember;

    /**
     * Optional. A chat title was changed to this value
     */
    @JsonProperty("new_chat_title")
    private String newChatTitle;

    /**
     * Optional. A chat photo was change to this value
     */
    @JsonProperty("new_chat_photo")
    private List<PhotoSize> newChatPhoto;

    /**
     * Optional. Service message: the chat photo was deleted
     */
    @JsonProperty("delete_chat_photo")
    private Boolean deleteChatPhoto;

    /**
     * Optional. Service message: the group has been created
     */
    @JsonProperty("group_chat_created")
    private Boolean groupChatCreated;

    /**
     * Optional. Service message: the supergroup has been created.
     * This field can't be received in a message coming through updates,
     * because bot can't be a member of a supergroup when it is created.
     * It can only be found in reply_to_message if someone replies
     * to a very first message in a directly created supergroup.
     */
    @JsonProperty("supergroup_chat_created")
    private Boolean supergroupChatCreated;

    /**
     * Optional. Service message: the channel has been created.
     * This field can't be received in a message coming through updates,
     * because bot can't be a member of a channel when it is created.
     * It can only be found in reply_to_message if someone replies
     * to a very first message in a channel.
     */
    @JsonProperty("channel_chat_created")
    private Boolean channelChatCreated;

    /**
     * Optional. Service message: auto-delete timer settings
     * changed in the chat
     */
    @JsonProperty("message_auto_delete_timer_changed")
    private MessageAutoDeleteTimerChanged messageAutoDeleteTimerChanged;

    /**
     * Optional. The group has been migrated to a supergroup
     * with the specified identifier.
     * <p>
     * This number may have more than 32 significant bits
     * and some programming languages may have difficulty/silent
     * defects in interpreting it. But it has at most 52 significant bits,
     * so a signed 64-bit integer or double-precision float type
     * are safe for storing this identifier.
     */
    @JsonProperty("migrate_to_chat_id")
    private Long migrateToChatId;

    /**
     * Optional. The supergroup has been migrated from a group
     * with the specified identifier.
     * <p>
     * This number may have more than 32 significant bits
     * and some programming languages may have difficulty/silent
     * defects in interpreting it. But it has at most 52 significant bits,
     * so a signed 64-bit integer or double-precision float type
     * are safe for storing this identifier.
     */
    @JsonProperty("migrate_from_chat_id")
    private Long migrateFromChatId;

    /**
     * Optional. Specified message was pinned.
     * Note that the Message object in this field
     * will not contain further reply_to_message fields
     * even if it itself is a reply.
     */
    @JsonProperty("pinned_message")
    private MaybeInaccessibleMessage pinnedMessage;

    /**
     * Optional. Message is an invoice for a <a href="https://core.telegram.org/bots/api#payments">payment</a>,
     * information about the invoice.
     * <a href="https://core.telegram.org/bots/api#payments">More about payments »</a>
     */
    @JsonProperty("invoice")
    private Invoice invoice;

    /**
     * Optional. Message is a service message about a successful payment,
     * information about the payment.
     * <a href="https://core.telegram.org/bots/api#payments">More about payments »</a>
     */
    @JsonProperty("successful_payment")
    private SuccessfulPayment successfulPayment;

    /**
     * Optional. Service message: a user was shared with the bot
     */
    @JsonProperty("users_shared")
    private UsersShared usersShared;

    /**
     * Optional. Service message: a chat was shared with the bot
     */
    @JsonProperty("chat_shared")
    private ChatShared chatShared;

    /**
     * Optional. The domain name of the website on which
     * the user has logged in.
     * <a href="https://core.telegram.org/widgets/login">More about Telegram Login »</a>
     */
    @JsonProperty("connected_website")
    private String connectedWebsite;

    /**
     * Optional. Service message: the user allowed the bot
     * to write messages after adding it to the attachment
     * or side menu, launching a Web App from a link,
     * or accepting an explicit request from a Web App
     * sent by the method <a href="https://core.telegram.org/bots/webapps#initializing-mini-apps">requestWriteAccess</a>
     */
    @JsonProperty("write_access_allowed")
    private WriteAccessAllowed writeAccessAllowed;

    /**
     * Optional. Telegram Passport data
     */
    @JsonProperty("passport_data")
    private PassportData passportData;

    /**
     * Optional. Service message. A user in the chat
     * triggered another user's proximity alert
     * while sharing Live Location.
     */
    @JsonProperty("proximity_alert_triggered")
    private ProximityAlertTriggered proximityAlertTriggered;

    /**
     * Optional. Service message: user boosted the chat
     */
    @JsonProperty("boost_added")
    private ChatBoostAdded boostAdded;

    /**
     * Optional. Service message: forum topic created
     */
    @JsonProperty("forum_topic_created")
    private ForumTopicCreated forumTopicCreated;

    /**
     * Optional. Service message: forum topic edited
     */
    @JsonProperty("forum_topic_edited")
    private ForumTopicEdited forumTopicEdited;

    /**
     * Optional. Service message: forum topic closed
     */
    @JsonProperty("forum_topic_closed")
    private ForumTopicClosed forumTopicClosed;

    /**
     * Optional. Service message: forum topic reopened
     */
    @JsonProperty("forum_topic_reopened")
    private ForumTopicReopened forumTopicReopened;

    /**
     * Optional. Service message: the 'General' forum topic hidden
     */
    @JsonProperty("general_forum_topic_hidden")
    private GeneralForumTopicHidden generalForumTopicHidden;

    /**
     * Optional. Service message: the 'General' forum topic unhidden
     */
    @JsonProperty("general_forum_topic_unhidden")
    private GeneralForumTopicUnhidden generalForumTopicUnhidden;

    /**
     * Optional. Service message: a scheduled giveaway was created
     */
    @JsonProperty("giveaway_created")
    private GiveawayCreated giveawayCreated;

    /**
     * Optional. The message is a scheduled giveaway message
     */
    @JsonProperty("giveaway")
    private Giveaway giveaway;

    /**
     * Optional. A giveaway with public winners was completed
     */
    @JsonProperty("giveaway_winners")
    private GiveawayWinners giveawayWinners;

    /**
     * Optional. Service message: a giveaway
     * without public winners was completed
     */
    @JsonProperty("giveaway_completed")
    private GiveawayCompleted giveawayCompleted;

    /**
     * Optional. Service message: video chat scheduled
     */
    @JsonProperty("video_chat_scheduled")
    private VideoChatScheduled videoChatScheduled;

    /**
     * Optional. Service message: video chat started
     */
    @JsonProperty("video_chat_started")
    private VideoChatStarted videoChatStarted;

    /**
     * Optional. Service message: video chat ended
     */
    @JsonProperty("video_chat_ended")
    private VideoChatEnded videoChatEnded;

    /**
     * Optional. Service message: new participants
     * invited to a video chat
     */
    @JsonProperty("video_chat_participants_invited")
    private VideoChatParticipantsInvited videoChatParticipantsInvited;

    /**
     * Optional. Service message: data sent by a Web App
     */
    @JsonProperty("web_app_data")
    private WebAppData webAppData;

    /**
     * Optional. Inline keyboard attached to the message.
     * {@code login_url} buttons are represented
     * as ordinary {@code url} buttons.
     */
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;
}
