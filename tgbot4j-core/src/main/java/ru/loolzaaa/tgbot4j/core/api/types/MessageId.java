package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a unique message identifier.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageId {
    /**
     * Unique message identifier.
     * In specific instances (e.g., message containing a video sent to a big chat),
     * the server might automatically schedule a message
     * instead of sending it immediately. In such cases,
     * this field will be 0 and the relevant message
     * will be unusable until it is actually sent
     */
    @JsonProperty("message_id")
    private Integer messageId;
}
