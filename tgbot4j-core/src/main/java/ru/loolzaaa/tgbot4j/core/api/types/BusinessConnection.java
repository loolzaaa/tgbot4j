package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes the connection of the bot
 * with a business account.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessConnection {
    /**
     * Unique identifier of the business connection
     */
    @JsonProperty("id")
    private String id;

    /**
     * Business account user that created the business connection
     */
    @JsonProperty("user")
    private User user;

    /**
     * Identifier of a private chat with the user
     * who created the business connection. This number
     * may have more than 32 significant bits
     * and some programming languages may have difficulty/silent defects
     * in interpreting it. But it has at most 52 significant bits,
     * so a 64-bit integer or double-precision float type
     * are safe for storing this identifier.
     */
    @JsonProperty("user_chat_id")
    private Long userChatId;

    /**
     * Date the connection was established in Unix time
     */
    @JsonProperty("date")
    private Integer date;

    /**
     * Optional. Rights of the business bot
     */
    @JsonProperty("rights")
    private BusinessBotRights rights;

    /**
     * True, if the connection is active
     */
    @JsonProperty("is_enabled")
    private Boolean isEnabled;
}
