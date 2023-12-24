package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a {@link ChatMember} that has
 * no additional privileges or restrictions.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMemberMember {
    /**
     * The member's status in the chat, always “member”
     */
    @JsonProperty("status")
    private String status;

    /**
     * Information about the user
     */
    @JsonProperty("user")
    private User user;
}
