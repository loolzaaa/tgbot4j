package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The message was originally sent on behalf
 * of a chat to a group chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageOriginChat implements MessageOrigin {
    /**
     * Type of the message origin, always “chat”
     */
    @JsonProperty("type")
    private String type;

    /**
     * Date the message was sent originally in Unix time
     */
    @JsonProperty("date")
    private Integer date;

    /**
     * Chat that sent the message originally
     */
    @JsonProperty("sender_chat")
    private Chat senderChat;

    /**
     * Optional. For messages originally sent by an anonymous
     * chat administrator, original message author signature
     */
    @JsonProperty("author_signature")
    private String authorSignature;
}
