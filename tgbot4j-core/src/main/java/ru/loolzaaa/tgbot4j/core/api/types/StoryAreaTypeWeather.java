package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a story area containing weather information.
 * Currently, a story can have up to 3 weather areas.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryAreaTypeWeather implements StoryAreaType {
    /**
     * Type of the area, always “weather”
     */
    @JsonProperty("type")
    private String type;

    /**
     * Temperature, in degree Celsius
     */
    @JsonProperty("temperature")
    private Double temperature;

    /**
     * Emoji representing the weather
     */
    @JsonProperty("emoji")
    private String emoji;

    /**
     * A color of the area background in the ARGB format
     */
    @JsonProperty("background_color")
    private Integer backgroundColor;
}
