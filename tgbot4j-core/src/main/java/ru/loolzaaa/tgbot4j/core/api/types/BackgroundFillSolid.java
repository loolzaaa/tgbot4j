package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The background is filled using the selected color.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BackgroundFillSolid implements BackgroundFill {
    /**
     * Type of the background fill, always “solid”
     */
    @JsonProperty("type")
    private String type;

    /**
     * The color of the background fill in the RGB24 format
     */
    @JsonProperty("color")
    private Integer color;
}
