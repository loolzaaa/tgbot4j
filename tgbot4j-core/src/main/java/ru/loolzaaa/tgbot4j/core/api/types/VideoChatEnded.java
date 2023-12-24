package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a service message
 * about a video chat ended in the chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoChatEnded {
    /**
     * Video chat duration in seconds
     */
    @JsonProperty("duration")
    private Integer duration;
}
