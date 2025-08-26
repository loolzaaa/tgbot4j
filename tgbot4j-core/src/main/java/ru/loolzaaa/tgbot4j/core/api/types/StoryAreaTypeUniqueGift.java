package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a story area pointing to a unique gift.
 * Currently, a story can have at most 1 unique gift area.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryAreaTypeUniqueGift implements StoryAreaType {
    /**
     * Type of the area, always “unique_gift”
     */
    @JsonProperty("type")
    private String type;

    /**
     * Unique name of the gift
     */
    @JsonProperty("name")
    private String name;
}
