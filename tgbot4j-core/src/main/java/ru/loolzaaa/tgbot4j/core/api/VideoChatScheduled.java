package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a service message
 * about a video chat scheduled in the chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoChatScheduled {
    /**
     * Point in time (Unix timestamp) when the video chat
     * is supposed to be started by a chat administrator
     */
    @JsonProperty("start_date")
    private Integer startDate;
}
