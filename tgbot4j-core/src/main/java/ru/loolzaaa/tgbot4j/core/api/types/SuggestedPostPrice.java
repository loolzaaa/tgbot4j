package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes the price of a suggested post.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuggestedPostPrice {
    /**
     * Currency in which the post will be paid.
     * Currently, must be one of “XTR” for Telegram Stars
     * or “TON” for toncoins
     */
    @JsonProperty("currency")
    private String currency;

    /**
     * The amount of the currency that will be paid for the post
     * in the smallest units of the currency, i.e. Telegram Stars
     * or nanotoncoins. Currently, price in Telegram Stars must be
     * between 5 and 100000, and price in nanotoncoins must be
     * between 10000000 and 10000000000000.
     */
    @JsonProperty("amount")
    private Integer amount;
}
