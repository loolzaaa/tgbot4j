package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents the {@link InputMessageContent} of an invoice message
 * to be sent as the result of an inline query.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputInvoiceMessageContent {
    /**
     * Product name, 1-32 characters
     */
    @JsonProperty("title")
    private String title;

    /**
     * Product description, 1-255 characters
     */
    @JsonProperty("description")
    private String description;

    /**
     * Bot-defined invoice payload, 1-128 bytes.
     * This will not be displayed to the user,
     * use for your internal processes.
     */
    @JsonProperty("payload")
    private String payload;

    /**
     * Payment provider token, obtained via @BotFather
     */
    @JsonProperty("provider_token")
    private String providerToken;

    /**
     * Three-letter ISO 4217 currency code, see <a href="https://core.telegram.org/bots/payments#supported-currencies">more on currencies</a>
     */
    @JsonProperty("currency")
    private String currency;

    /**
     * Price breakdown, a JSON-serialized list of components
     * (e.g. product price, tax, discount, delivery cost,
     * delivery tax, bonus, etc.)
     */
    @JsonProperty("prices")
    private List<LabeledPrice> prices;

    /**
     * Optional. The maximum accepted amount for tips in the smallest
     * units of the currency (integer, not float/double).
     * For example, for a maximum tip of {@code US$ 1.45} pass {@code max_tip_amount = 145}.
     * See the exp parameter in currencies.json, it shows the number of digits
     * past the decimal point for each currency (2 for the majority of currencies).
     * Defaults to 0
     */
    @JsonProperty("max_tip_amount")
    private Integer maxTipAmount;

    /**
     * Optional. A JSON-serialized array of suggested amounts of tip
     * in the smallest units of the currency (integer, <b>not</b> float/double).
     * At most 4 suggested tip amounts can be specified.
     * The suggested tip amounts must be positive,
     * passed in a strictly increased order and must
     * not exceed max_tip_amount.
     */
    @JsonProperty("suggested_tip_amounts")
    private List<Integer> suggestedTipAmounts;

    /**
     * Optional. A JSON-serialized object for data about the invoice,
     * which will be shared with the payment provider.
     * A detailed description of the required fields
     * should be provided by the payment provider.
     */
    @JsonProperty("provider_data")
    private String providerData;

    /**
     * Optional. URL of the product photo for the invoice.
     * Can be a photo of the goods or a marketing image for a service.
     */
    @JsonProperty("photo_url")
    private String photoUrl;

    /**
     * Optional. Photo size in bytes
     */
    @JsonProperty("photo_size")
    private Integer photoSize;

    /**
     * Optional. Photo width
     */
    @JsonProperty("photo_width")
    private Integer photoWidth;

    /**
     * Optional. Photo height
     */
    @JsonProperty("photo_height")
    private Integer photoHeight;

    /**
     * Optional. Pass True if you require the user's full name
     * to complete the order
     */
    @JsonProperty("need_name")
    private Boolean needName;

    /**
     * Optional. Pass True if you require the user's phone number
     * to complete the order
     */
    @JsonProperty("need_phone_number")
    private Boolean needPhoneNumber;

    /**
     * Optional. Pass True if you require the user's email address
     * to complete the order
     */
    @JsonProperty("need_email")
    private Boolean needEmail;

    /**
     * Optional. Pass True if you require the user's shipping address
     * to complete the order
     */
    @JsonProperty("need_shipping_address")
    private Boolean needShippingAddress;

    /**
     * Optional. Pass True if the user's phone number should be sent
     * to provider
     */
    @JsonProperty("send_phone_number_to_provider")
    private Boolean sendPhoneNumberToProvider;

    /**
     * Optional. Pass True if the user's email address should be sent
     * to provider
     */
    @JsonProperty("send_email_to_provider")
    private Boolean sendEmailToProvider;

    /**
     * Optional. Pass True if the final price depends on the shipping method
     */
    @JsonProperty("is_flexible")
    private Boolean isFlexible;
}
