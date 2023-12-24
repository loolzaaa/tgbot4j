package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object contains basic information about a successful payment.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessfulPayment {
    /**
     * Three-letter ISO 4217 <a href="https://core.telegram.org/bots/payments#supported-currencies">currency code</a>
     */
    @JsonProperty("currency")
    private String currency;

    /**
     * Total price in the smallest units of the currency (integer, not float/double).
     * For example, for a price of {@code US$ 1.45} pass {@code amount = 145}. See the exp parameter in currencies.json,
     * it shows the number of digits past the decimal point for each currency (2 for the majority of currencies).
     */
    @JsonProperty("total_amount")
    private Integer totalAmount;

    /**
     * Bot specified invoice payload
     */
    @JsonProperty("invoice_payload")
    private String invoicePayload;

    /**
     * Optional. Identifier of the shipping option chosen by the user
     */
    @JsonProperty("shipping_option_id")
    private String shippingOptionId;

    /**
     * Optional. Order information provided by the user
     */
    @JsonProperty("order_info")
    private OrderInfo orderInfo;

    /**
     * Telegram payment identifier
     */
    @JsonProperty("telegram_payment_charge_id")
    private String telegramPaymentChargeId;

    /**
     * Provider payment identifier
     */
    @JsonProperty("provider_payment_charge_id")
    private String providerPaymentChargeId;
}
