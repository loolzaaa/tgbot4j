package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a join request sent to a chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatJoinRequest {
    /**
     * Chat to which the request was sent
     */
    @JsonProperty("chat")
    private Chat chat;

    /**
     * User that sent the join request
     */
    @JsonProperty("from")
    private User from;

    /**
     * Identifier of a private chat with the user who sent the join request.
     * This number may have more than 32 significant bits
     * and some programming languages may have difficulty/silent
     * defects in interpreting it. But it has at most 52 significant bits,
     * so a 64-bit integer or double-precision float type
     * are safe for storing this identifier.
     * The bot can use this identifier for 5 minutes to send messages
     * until the join request is processed,
     * assuming no other administrator contacted the user.
     */
    @JsonProperty("user_chat_id")
    private Long userChatId;

    /**
     * Date the request was sent in Unix time
     */
    @JsonProperty("date")
    private Integer date;

    /**
     * Optional. Bio of the user.
     */
    @JsonProperty("bio")
    private String bio;

    /**
     * Optional. Chat invite link that was used by the user
     * to send the join request
     */
    @JsonProperty("invite_link")
    private ChatInviteLink inviteLink;
}
