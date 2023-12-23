package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a service message about a change in auto-delete timer settings.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageAutoDeleteTimerChanged {
    /**
     * New auto-delete time for messages in the chat; in seconds
     */
    @JsonProperty("message_auto_delete_time")
    private Integer messageAutoDeleteTime;
}
