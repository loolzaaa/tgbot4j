package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object represents one shipping option.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingOption {
    /**
     * Shipping option identifier
     */
    @JsonProperty("id")
    private String id;

    /**
     * Option title
     */
    @JsonProperty("title")
    private String title;

    /**
     * List of price portions
     */
    @JsonProperty("prices")
    private List<LabeledPrice> prices;
}
