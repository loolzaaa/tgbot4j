package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes an amount of Telegram Stars.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StarAmount {
    /**
     * Integer amount of Telegram Stars, rounded to 0;
     * can be negative
     */
    @JsonProperty("amount")
    private Integer amount;

    /**
     * Optional. The number of 1/1000000000 shares of Telegram Stars;
     * from -999999999 to 999999999;
     * can be negative if and only if amount is non-positive
     */
    @JsonProperty("nanostar_amount")
    private Integer nanostarAmount;
}
