package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The background is a gradient fill.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BackgroundFillGradient implements BackgroundFill {
    /**
     * Type of the background fill, always “gradient”
     */
    @JsonProperty("type")
    private String type;

    /**
     * Top color of the gradient in the RGB24 format
     */
    @JsonProperty("top_color")
    private Integer topColor;

    /**
     * Bottom color of the gradient in the RGB24 format
     */
    @JsonProperty("bottom_color")
    private Integer bottomColor;

    /**
     * Clockwise rotation angle of the background fill
     * in degrees; 0-359
     */
    @JsonProperty("rotation_angle\t")
    private Integer rotationAngle;
}
