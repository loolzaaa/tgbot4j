package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The background is automatically filled
 * based on the selected colors.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BackgroundTypeFill implements BackgroundType {
    /**
     * Type of the background, always “fill”
     */
    @JsonProperty("type")
    private String type;

    /**
     * The background fill
     */
    @JsonProperty("fill")
    private BackgroundFill fill;

    /**
     * Dimming of the background in dark themes,
     * as a percentage; 0-100
     */
    @JsonProperty("dark_theme_dimming")
    private Integer darkThemeDimming;
}
