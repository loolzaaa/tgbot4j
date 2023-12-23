package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a location to which a chat is connected.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatLocation {
    /**
     * The location to which the supergroup is connected.
     * Can't be a live location.
     */
    @JsonProperty("location")
    private Location location;

    /**
     * Location address; 1-64 characters, as defined by the chat owner
     */
    @JsonProperty("address")
    private String address;
}
