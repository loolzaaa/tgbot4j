package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object contains information about the users whose identifiers
 * were shared with the bot using a {@link KeyboardButtonRequestUsers} button.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersShared {
    /**
     * Identifier of the request
     */
    @JsonProperty("request_id")
    private Integer requestId;

    /**
     * Identifiers of the shared users. These numbers may have
     * more than 32 significant bits and some programming languages
     * may have difficulty/silent defects in interpreting them.
     * But they have at most 52 significant bits, so 64-bit integers
     * or double-precision float types are safe for storing
     * these identifiers. The bot may not have access to the users
     * and could be unable to use these identifiers, unless the users
     * are already known to the bot by some other means.
     */
    @JsonProperty("user_ids")
    private List<Long> userIds;
}
