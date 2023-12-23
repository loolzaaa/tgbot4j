package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object contains information about the user whose identifier
 * was shared with the bot using a {@link KeyboardButtonRequestUser} button.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserShared {
    /**
     * Identifier of the request
     */
    @JsonProperty("request_id")
    private Integer requestId;

    /**
     * Identifier of the shared user. This number may have more than 32 significant bits
     * and some programming languages may have difficulty/silent defects in interpreting it.
     * But it has at most 52 significant bits, so a 64-bit integer or double-precision float type
     * are safe for storing this identifier. The bot may not have access to the user
     * and could be unable to use this identifier, unless the user
     * is already known to the bot by some other means.
     */
    @JsonProperty("user_id")
    private Long userId;
}
