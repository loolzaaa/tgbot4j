package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.methods.GetMe;

/**
 * This object represents a Telegram user or bot.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * Unique identifier for this user or bot.
     * This number may have more than 32 significant bits
     * and some programming languages may have difficulty/silent
     * defects in interpreting it. But it has at most 52 significant bits,
     * so a 64-bit integer or double-precision float type
     * are safe for storing this identifier.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * True, if this user is a bot
     */
    @JsonProperty("is_bot")
    private Boolean isBot;

    /**
     * User's or bot's first name
     */
    @JsonProperty("first_name")
    private String firstName;

    /**
     * Optional. User's or bot's last name
     */
    @JsonProperty("last_name")
    private String lastName;

    /**
     * Optional. User's or bot's username
     */
    @JsonProperty("username")
    private String username;

    /**
     * Optional. <a href="https://en.wikipedia.org/wiki/IETF_language_tag">IETF language tag</a>
     * of the user's language
     */
    @JsonProperty("language_code")
    private String languageCode;

    /**
     * Optional. True, if this user is a Telegram Premium user
     */
    @JsonProperty("is_premium")
    private Boolean isPremium;

    /**
     * Optional. True, if this user added the bot to the attachment menu
     */
    @JsonProperty("added_to_attachment_menu")
    private Boolean addedToAttachmentMenu;

    /**
     * Optional. True, if the bot can be invited to groups.
     * Returned only in {@link GetMe}.
     */
    @JsonProperty("can_join_groups")
    private Boolean canJoinGroups;

    /**
     * Optional. True, if <a href="https://core.telegram.org/bots/features#privacy-mode">privacy mode</a>
     * is disabled for the bot.
     * Returned only in {@link GetMe}.
     */
    @JsonProperty("can_read_all_group_messages")
    private Boolean canReadAllGroupMessages;

    /**
     * Optional. True, if the bot supports inline queries.
     * Returned only in {@link GetMe}.
     */
    @JsonProperty("supports_inline_queries")
    private Boolean supportsInlineQueries;

    /**
     * Optional. True, if the bot can be connected
     * to a Telegram Business account to receive its messages.
     * Returned only in {@link GetMe}.
     */
    @JsonProperty("can_connect_to_business")
    private Boolean canConnectToBusiness;
}
