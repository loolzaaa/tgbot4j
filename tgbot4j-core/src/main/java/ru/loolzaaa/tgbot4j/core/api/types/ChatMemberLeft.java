package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a {@link ChatMember} that isn't currently a member
 * of the chat, but may join it themselves.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMemberLeft implements ChatMember {
    /**
     * The member's status in the chat, always “left”
     */
    @JsonProperty("status")
    private String status;

    /**
     * Information about the user
     */
    @JsonProperty("user")
    private User user;
}
