package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The message was originally sent to a channel chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageOriginChannel implements MessageOrigin {
    /**
     * Type of the message origin, always “channel”
     */
    @JsonProperty("type")
    private String type;

    /**
     * Date the message was sent originally in Unix time
     */
    @JsonProperty("date")
    private Integer date;

    /**
     * Channel chat to which the message was originally sent
     */
    @JsonProperty("chat")
    private Chat chat;

    /**
     * Unique message identifier inside the chat
     */
    @JsonProperty("message_id")
    private Integer messageId;

    /**
     * Optional. Signature of the original post author
     */
    @JsonProperty("author_signature")
    private String authorSignature;
}
