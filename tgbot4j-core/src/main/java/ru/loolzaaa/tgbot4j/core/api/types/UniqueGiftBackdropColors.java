package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object describes the colors of the backdrop
 * of a unique gift.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniqueGiftBackdropColors {
    /**
     * The color in the center of the backdrop in RGB format
     */
    @JsonProperty("center_color")
    private Integer centerColor;

    /**
     * The color on the edges of the backdrop in RGB format
     */
    @JsonProperty("edge_color")
    private Integer edgeColor;

    /**
     * The color to be applied to the symbol in RGB format
     */
    @JsonProperty("symbol_color")
    private Integer symbolColor;

    /**
     * The color for the text on the backdrop in RGB format
     */
    @JsonProperty("text_color")
    private Integer textColor;
}
