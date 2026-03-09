package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object describes the background of a gift.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftBackground {
    /**
     * Center color of the background in RGB format
     */
    @JsonProperty("center_color")
    private Integer centerColor;

    /**
     * Edge color of the background in RGB format
     */
    @JsonProperty("edge_color")
    private Integer edgeColor;

    /**
     * Text color of the background in RGB format
     */
    @JsonProperty("text_color")
    private Integer textColor;
}
