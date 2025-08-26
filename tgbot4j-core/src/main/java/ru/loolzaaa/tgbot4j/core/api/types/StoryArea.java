package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a clickable area on a story media.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryArea {
    /**
     * Position of the area
     */
    @JsonProperty("position")
    private StoryAreaPosition position;

    /**
     * Type of the area
     */
    @JsonProperty("type")
    private StoryAreaType type;
}
