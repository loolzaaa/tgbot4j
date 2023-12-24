package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a {@link ChatMember} that was banned in the chat
 * and can't return to the chat or view chat messages.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMemberBanned implements ChatMember {
    /**
     * The member's status in the chat, always “kicked”
     */
    @JsonProperty("status")
    private String status;

    /**
     * Information about the user
     */
    @JsonProperty("user")
    private User user;

    /**
     * Date when restrictions will be lifted for this user;
     * Unix time. If 0, then the user is banned forever
     */
    @JsonProperty("until_date")
    private Integer untilDate;
}
