package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object contains information about a user
 * that was shared with the bot
 * using a {@link KeyboardButtonRequestUsers} button.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedUser {
    /**
     * Identifier of the shared user. This number may have more
     * than 32 significant bits and some programming languages
     * may have difficulty/silent defects in interpreting it.
     * But it has at most 52 significant bits, so 64-bit integers
     * or double-precision float types are safe
     * for storing these identifiers.
     * The bot may not have access to the user and could be unable
     * to use this identifier, unless the user is already known
     * to the bot by some other means.
     */
    @JsonProperty("user_id")
    private Long userId;

    /**
     * Optional. First name of the user, if the name
     * was requested by the bot
     */
    @JsonProperty("first_name")
    private String firstName;

    /**
     * Optional. Last name of the user, if the name
     * was requested by the bot
     */
    @JsonProperty("last_name")
    private String lastName;

    /**
     * Optional. Username of the user, if the username
     * was requested by the bot
     */
    @JsonProperty("username")
    private String username;

    /**
     * Optional. Available sizes of the chat photo,
     * if the photo was requested by the bot
     */
    @JsonProperty("photo")
    private List<PhotoSize> photo;
}
