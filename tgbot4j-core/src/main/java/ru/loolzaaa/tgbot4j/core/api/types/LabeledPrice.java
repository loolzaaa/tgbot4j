package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a portion of the price
 * for goods or services.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabeledPrice {
    /**
     * Portion label
     */
    @JsonProperty("label")
    private String label;

    /**
     * Price of the product in the smallest units
     * of the <a href="https://core.telegram.org/bots/payments#supported-currencies">currency</a> (integer, <b>not</b> float/double).
     * For example, for a price of {@code US$ 1.45} pass {@code amount = 145}.
     * See the exp parameter in currencies.json,
     * it shows the number of digits past the decimal point
     * for each currency (2 for the majority of currencies).
     */
    @JsonProperty("amount")
    private Integer amount;
}
