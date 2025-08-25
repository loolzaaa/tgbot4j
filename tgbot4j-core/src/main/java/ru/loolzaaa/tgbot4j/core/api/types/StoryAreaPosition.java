package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes the position of a clickable area within a story.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryAreaPosition {
    /**
     * The abscissa of the area's center, as a percentage of the media width
     */
    @JsonProperty("x_percentage")
    private Double xPercentage;

    /**
     * The ordinate of the area's center, as a percentage of the media height
     */
    @JsonProperty("y_percentage")
    private Double yPercentage;

    /**
     * The width of the area's rectangle, as a percentage of the media width
     */
    @JsonProperty("width_percentage")
    private Double widthPercentage;

    /**
     * The height of the area's rectangle, as a percentage of the media height
     */
    @JsonProperty("height_percentage")
    private Double heightPercentage;

    /**
     * The clockwise rotation angle of the rectangle, in degrees; 0-360
     */
    @JsonProperty("rotation_angle")
    private Double rotationAngle;

    /**
     * The radius of the rectangle corner rounding, as a percentage of the media width
     */
    @JsonProperty("corner_radius_percentage")
    private Double cornerRadiusPercentage;
}
