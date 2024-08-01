package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object contains basic information about an invoice.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    /**
     * Product name
     */
    @JsonProperty("title")
    private String title;

    /**
     * Product description
     */
    @JsonProperty("description")
    private String description;

    /**
     * Unique bot deep-linking parameter that can be used to generate this invoice
     */
    @JsonProperty("start_parameter")
    private String startParameter;

    /**
     * Three-letter ISO 4217 <a href="https://core.telegram.org/bots/payments#supported-currencies">currency code</a>,
     * or “XTR” for payments in <a href="https://t.me/BotNews/90">Telegram Stars</a>
     */
    @JsonProperty("currency")
    private String currency;

    /**
     * Total price in the smallest units of the currency (integer, <b>not</b> float/double).
     * For example, for a price of {@code US$ 1.45} pass {@code amount = 145}.
     * See the exp parameter in currencies.json, it shows the number of digits
     * past the decimal point for each currency (2 for the majority of currencies).
     */
    @JsonProperty("total_amount")
    private Integer totalAmount;
}
