package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The background is a freeform gradient that rotates
 * after every message in the chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BackgroundFillFreeformGradient implements BackgroundFill {
    /**
     * Type of the background fill, always “freeform_gradient”
     */
    @JsonProperty("type")
    private String type;

    /**
     * A list of the 3 or 4 base colors that are used
     * to generate the freeform gradient in the RGB24 format
     */
    @JsonProperty("colors")
    private List<Integer> colors;
}
