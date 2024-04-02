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
     * Information about users shared with the bot.
     */
    @JsonProperty("users")
    private List<SharedUser> users;
}
