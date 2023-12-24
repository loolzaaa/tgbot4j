package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a {@link ChatMember} that owns the chat
 * and has all administrator privileges.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMemberOwner {
    /**
     * The member's status in the chat, always “creator”
     */
    @JsonProperty("status")
    private String status;

    /**
     * Information about the user
     */
    @JsonProperty("user")
    private User user;

    /**
     * True, if the user's presence in the chat is hidden
     */
    @JsonProperty("is_anonymous")
    private Boolean isAnonymous;

    /**
     * Optional. Custom title for this user
     */
    @JsonProperty("custom_title")
    private String customTitle;
}
