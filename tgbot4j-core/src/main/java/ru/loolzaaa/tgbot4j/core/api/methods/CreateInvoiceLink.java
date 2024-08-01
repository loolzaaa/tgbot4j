package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.LabeledPrice;

import java.util.List;

/**
 * Use this method to create a link for an invoice.
 * Returns the created invoice link as String on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceLink implements TelegramMethod<String> {
    /**
     * Product name, 1-32 characters
     */
    @Required
    @JsonProperty("title")
    private String title;

    /**
     * Product description, 1-255 characters
     */
    @Required
    @JsonProperty("description")
    private String description;

    /**
     * Bot-defined invoice payload, 1-128 bytes.
     * This will not be displayed to the user,
     * use for your internal processes.
     */
    @Required
    @JsonProperty("payload")
    private String payload;

    /**
     * Payment provider token, obtained via @BotFather.
     * Pass an empty string for payments
     * in <a href="https://t.me/BotNews/90">Telegram Stars</a>.
     */
    @JsonProperty("provider_token")
    private String providerToken;

    /**
     * Three-letter ISO 4217 currency code,
     * see <a href="https://core.telegram.org/bots/payments#supported-currencies">more on currencies</a>.
     * Pass “XTR” for payments in <a href="https://t.me/BotNews/90">Telegram Stars</a>.
     */
    @Required
    @JsonProperty("currency")
    private String currency;

    /**
     * Price breakdown, a JSON-serialized list of components
     * (e.g. product price, tax, discount, delivery cost,
     * delivery tax, bonus, etc.).
     * Must contain exactly one item for payments
     * in <a href="https://t.me/BotNews/90">Telegram Stars</a>.
     */
    @Required
    @JsonProperty("prices")
    private List<LabeledPrice> prices;

    /**
     * The maximum accepted amount for tips in the smallest
     * units of the currency (integer, not float/double).
     * For example, for a maximum tip of {@code US$ 1.45} pass {@code max_tip_amount = 145}.
     * See the exp parameter in currencies.json, it shows the number
     * of digits past the decimal point for each currency
     * (2 for the majority of currencies). Defaults to 0.
     * Not supported for payments in <a href="https://t.me/BotNews/90">Telegram Stars</a>.
     */
    @JsonProperty("max_tip_amount")
    private Integer maxTipAmount;

    /**
     * A JSON-serialized array of suggested amounts of tips
     * in the smallest units of the currency (integer, <b>not</b> float/double).
     * At most 4 suggested tip amounts can be specified.
     * The suggested tip amounts must be positive,
     * passed in a strictly increased order
     * and must not exceed max_tip_amount.
     */
    @JsonProperty("suggested_tip_amounts")
    private List<Integer> suggestedTipAmounts;

    /**
     * JSON-serialized data about the invoice,
     * which will be shared with the payment provider.
     * A detailed description of required fields
     * should be provided by the payment provider.
     */
    @JsonProperty("provider_data")
    private String providerData;

    /**
     * URL of the product photo for the invoice.
     * Can be a photo of the goods or a marketing image
     * for a service.
     */
    @JsonProperty("photo_url")
    private String photoUrl;

    /**
     * Photo size in bytes
     */
    @JsonProperty("photo_size")
    private Integer photoSize;

    /**
     * Photo width
     */
    @JsonProperty("photo_width")
    private Integer photoWidth;

    /**
     * Photo height
     */
    @JsonProperty("photo_height")
    private Integer photoHeight;

    /**
     * Pass True if you require the user's full name
     * to complete the order.
     * Ignored for payments in <a href="https://t.me/BotNews/90">Telegram Stars</a>.
     */
    @JsonProperty("need_name")
    private Boolean needName;

    /**
     * Pass True if you require the user's phone number
     * to complete the order.
     * Ignored for payments in <a href="https://t.me/BotNews/90">Telegram Stars</a>.
     */
    @JsonProperty("need_phone_number")
    private Boolean needPhoneNumber;

    /**
     * Pass True if you require the user's email address
     * to complete the order.
     * Ignored for payments in <a href="https://t.me/BotNews/90">Telegram Stars</a>.
     */
    @JsonProperty("need_email")
    private Boolean needEmail;

    /**
     * Pass True if you require the user's shipping address
     * to complete the order.
     * Ignored for payments in <a href="https://t.me/BotNews/90">Telegram Stars</a>.
     */
    @JsonProperty("need_shipping_address")
    private Boolean needShippingAddress;

    /**
     * Pass True if the user's phone number should be sent to the provider.
     * Ignored for payments in <a href="https://t.me/BotNews/90">Telegram Stars</a>.
     */
    @JsonProperty("send_phone_number_to_provider")
    private Boolean sendPhoneNumberToProvider;

    /**
     * Pass True if the user's email address should be sent to the provider.
     * Ignored for payments in <a href="https://t.me/BotNews/90">Telegram Stars</a>.
     */
    @JsonProperty("send_email_to_provider")
    private Boolean sendEmailToProvider;

    /**
     * Pass True if the final price depends on the shipping method.
     * Ignored for payments in <a href="https://t.me/BotNews/90">Telegram Stars</a>.
     */
    @JsonProperty("is_flexible")
    private Boolean isFlexible;

    @Override
    public String determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, String.class);
    }
}
