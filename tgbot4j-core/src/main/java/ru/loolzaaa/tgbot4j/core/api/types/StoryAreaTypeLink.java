package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a story area pointing to an HTTP or tg:// link.
 * Currently, a story can have up to 3 link areas.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryAreaTypeLink implements StoryAreaType {
    /**
     * Type of the area, always “link”
     */
    @JsonProperty("type")
    private String type;

    /**
     * HTTP or tg:// URL to be opened when the area is clicked
     */
    @JsonProperty("url")
    private String url;
}
