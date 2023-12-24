package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents the content of a service message,
 * sent whenever a user in the chat triggers a proximity alert
 * set by another user.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProximityAlertTriggered {
    /**
     * User that triggered the alert
     */
    @JsonProperty("traveler")
    private User traveler;

    /**
     * User that set the alert
     */
    @JsonProperty("watcher")
    private User watcher;

    /**
     * The distance between the users
     */
    @JsonProperty("distance")
    private Integer distance;
}
