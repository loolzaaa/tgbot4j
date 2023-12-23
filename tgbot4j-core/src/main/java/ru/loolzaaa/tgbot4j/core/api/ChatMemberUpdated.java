package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents changes in the status of a chat member.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMemberUpdated {
    /**
     * Chat the user belongs to
     */
    @JsonProperty("chat")
    private Chat chat;

    /**
     * Performer of the action, which resulted in the change
     */
    @JsonProperty("from")
    private User from;

    /**
     * Date the change was done in Unix time
     */
    @JsonProperty("date")
    private Integer date;

    /**
     * Previous information about the chat member
     */
    @JsonProperty("old_chat_member")
    private ChatMember oldChatMember;

    /**
     * New information about the chat member
     */
    @JsonProperty("new_chat_member")
    private ChatMember newChatMember;

    /**
     * Optional. Chat invite link, which was used by the user to join the chat;
     * for joining by invite link events only.
     */
    @JsonProperty("invite_link")
    private ChatInviteLink inviteLink;

    /**
     * Optional. True, if the user joined the chat via a chat folder invite link
     */
    @JsonProperty("via_chat_folder_invite_link")
    private Boolean viaChatFolderInviteLink;
}
