package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.methods.GetChat;

import java.util.List;

/**
 * This object contains full information about a chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatFullInfo {
    /**
     * Unique identifier for this chat.
     * This number may have more than 32 significant bits
     * and some programming languages may have difficulty/silent
     * defects in interpreting it. But it has at most 52 significant bits,
     * so a signed 64-bit integer or double-precision float type
     * are safe for storing this identifier.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * Type of chat, can be either “private”,
     * “group”, “supergroup” or “channel”
     */
    @JsonProperty("type")
    private String type;

    /**
     * Optional. Title, for supergroups, channels
     * and group chats
     */
    @JsonProperty("title")
    private String title;

    /**
     * Optional. Username, for private chats, supergroups
     * and channels if available
     */
    @JsonProperty("username")
    private String username;

    /**
     * Optional. First name of the other party in a private chat
     */
    @JsonProperty("first_name")
    private String firstName;

    /**
     * Optional. Last name of the other party in a private chat
     */
    @JsonProperty("last_name")
    private String lastName;

    /**
     * Optional. True, if the supergroup chat
     * is a forum (has <a href="https://telegram.org/blog/topics-in-groups-collectible-usernames#topics-in-groups">topics</a> enabled)
     */
    @JsonProperty("is_forum")
    private Boolean isForum;

    /**
     * The maximum number of reactions that can be set
     * on a message in the chat
     */
    @JsonProperty("max_reaction_count")
    private Integer maxReactionCount;

    /**
     * Optional. Chat photo.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("photo")
    private ChatPhoto photo;

    /**
     * Optional. If non-empty, the list of all
     * <a href="https://telegram.org/blog/topics-in-groups-collectible-usernames#collectible-usernames">active chat usernames</a>;
     * for private chats, supergroups and channels.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("active_usernames")
    private List<String> activeUsernames;

    /**
     * Optional. For private chats, the date of birth
     * of the user.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("birthdate")
    private Birthdate birthdate;

    /**
     * Optional. For private chats with business accounts,
     * the intro of the business.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("business_intro")
    private BusinessIntro businessIntro;

    /**
     * Optional. For private chats with business accounts,
     * the location of the business.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("business_location")
    private BusinessLocation businessLocation;

    /**
     * Optional. For private chats with business accounts,
     * the opening hours of the business.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("business_opening_hours")
    private BusinessOpeningHours businessOpeningHours;

    /**
     * Optional. For private chats, the personal channel
     * of the user.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("personal_chat")
    private Chat personalChat;

    /**
     * Optional. List of available reactions allowed in the chat.
     * If omitted, then all {@link ReactionTypeEmoji} are allowed.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("available_reactions")
    private List<ReactionType> availableReactions;

    /**
     * Optional. Custom emoji identifier of emoji chosen
     * by the chat for the reply header and link preview background.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("background_custom_emoji_id")
    private String backgroundCustomEmojiId;

    /**
     * Optional. Identifier of the accent color for the chat's
     * profile background.
     * See <a href="https://core.telegram.org/bots/api#profile-accent-colors">profile accent colors</a> for more details.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("profile_accent_color_id")
    private Integer profileAccentColorId;

    /**
     * Optional. Custom emoji identifier of the emoji
     * chosen by the chat for its profile background.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("profile_background_custom_emoji_id")
    private String profileBackgroundCustomEmojiId;

    /**
     * Optional. Custom emoji identifier of the emoji status
     * of the chat or the other party in a private chat.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("emoji_status_custom_emoji_id")
    private String emojiStatusCustomEmojiId;

    /**
     * Optional. Expiration date of the emoji status
     * of the chat or the other party in a private chat,
     * in Unix time, if any.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("emoji_status_expiration_date")
    private Integer emojiStatusExpirationDate;

    /**
     * Optional. Bio of the other party in a private chat.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("bio")
    private String bio;

    /**
     * Optional. True, if privacy settings of the other party
     * in the private chat allows
     * to use {@code tg://user?id=<user_id>} links
     * only in chats with the user.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("has_private_forwards")
    private Boolean hasPrivateForwards;

    /**
     * Optional. True, if the privacy settings of the other party
     * restrict sending voice and video note messages
     * in the private chat.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("has_restricted_voice_and_video_messages")
    private Boolean hasRestrictedVoiceAndVideoMessages;

    /**
     * Optional. True, if users need to join the supergroup
     * before they can send messages.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("join_to_send_messages")
    private Boolean joinToSendMessages;

    /**
     * Optional. True, if all users directly joining
     * the supergroup need to be approved by supergroup
     * administrators.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("join_by_request")
    private Boolean joinByRequest;

    /**
     * Optional. Description, for groups, supergroups
     * and channel chats.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("description")
    private String description;

    /**
     * Optional. Primary invite link, for groups, supergroups
     * and channel chats.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("invite_link")
    private String inviteLink;

    /**
     * Optional. The most recent pinned message (by sending date).
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("pinned_message")
    private Message pinnedMessage;

    /**
     * Optional. Default chat member permissions,
     * for groups and supergroups.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("permissions")
    private ChatPermissions permissions;

    /**
     * Optional. For supergroups, the minimum allowed delay
     * between consecutive messages sent by each
     * unpriviledged user; in seconds.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("slow_mode_delay")
    private Integer slowModeDelay;

    /**
     * Optional. For supergroups, the minimum number
     * of boosts that a non-administrator user needs
     * to add in order to ignore slow mode
     * and chat permissions.
     * <p>
     * Returned only in {@link GetChat}.
     */

    @JsonProperty("unrestrict_boost_count")
    private Integer unrestrictBoostCount;

    /**
     * Optional. The time after which all messages sent
     * to the chat will be automatically deleted; in seconds.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("message_auto_delete_time")
    private Integer messageAutoDeleteTime;

    /**
     * Optional. True, if aggressive anti-spam checks
     * are enabled in the supergroup. The field
     * is only available to chat administrators.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("has_aggressive_anti_spam_enabled")
    private Boolean hasAggressiveAntiSpamEnabled;

    /**
     * Optional. True, if non-administrators can only get
     * the list of bots and administrators in the chat.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("has_hidden_members")
    private Boolean hasHiddenMembers;

    /**
     * Optional. True, if messages from the chat
     * can't be forwarded to other chats.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("has_protected_content")
    private Boolean hasProtectedContent;

    /**
     * Optional. True, if new chat members will have access
     * to old messages; available only to chat administrators.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("has_visible_history")
    private Boolean hasVisibleHistory;

    /**
     * Optional. For supergroups, name of group sticker set.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("sticker_set_name")
    private String stickerSetName;

    /**
     * Optional. True, if the bot can change the group sticker set.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("can_set_sticker_set")
    private Boolean canSetStickerSet;

    /**
     * Optional. For supergroups, the name of the group's
     * custom emoji sticker set.
     * <p>
     * Custom emoji from this set can be used by all users
     * and bots in the group.
     * <p>
     * Returned only in {@link GetChat}.
     */

    @JsonProperty("custom_emoji_sticker_set_name")
    private String customEmojiStickerSetName;

    /**
     * Optional. Unique identifier for the linked chat,
     * i.e. the discussion group identifier for a channel
     * and vice versa; for supergroups and channel chats.
     * <p>
     * This identifier may be greater than 32 bits
     * and some programming languages may have difficulty/silent
     * defects in interpreting it. But it is smaller than 52 bits,
     * so a signed 64 bit integer or double-precision float type
     * are safe for storing this identifier.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("linked_chat_id")
    private Long linkedChatId;

    /**
     * Optional. For supergroups, the location to which
     * the supergroup is connected.
     * <p>
     * Returned only in {@link GetChat}.
     */
    @JsonProperty("location")
    private ChatLocation location;
}
