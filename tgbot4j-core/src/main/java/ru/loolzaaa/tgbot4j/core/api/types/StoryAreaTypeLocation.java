package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a story area pointing to a location.
 * Currently, a story can have up to 10 location areas.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryAreaTypeLocation implements StoryAreaType {
    /**
     * Type of the area, always “location”
     */
    @JsonProperty("type")
    private String type;

    /**
     * Location latitude in degrees
     */
    @JsonProperty("latitude")
    private Double latitude;

    /**
     * Location longitude in degrees
     */
    @JsonProperty("longitude")
    private Double longitude;

    /**
     * Optional. Address of the location
     */
    @JsonProperty("address")
    private LocationAddress address;
}
