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
     * Unique message identifier
     */
    @JsonProperty("message_id")
    private Integer messageId;
}
