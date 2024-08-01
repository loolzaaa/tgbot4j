package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object is received when messages are deleted
 * from a connected business account.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessMessagesDeleted {
    /**
     * Unique identifier of the business connection
     */
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    /**
     * Information about a chat in the business account.
     * The bot may not have access to the chat
     * or the corresponding user.
     */
    @JsonProperty("chat")
    private Chat chat;

    /**
     * The list of identifiers of deleted messages
     * in the chat of the business account
     */
    @JsonProperty("message_ids")
    private List<Integer> messageIds;
}
