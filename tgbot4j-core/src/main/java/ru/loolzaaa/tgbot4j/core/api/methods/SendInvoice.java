package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.InlineKeyboardMarkup;
import ru.loolzaaa.tgbot4j.core.api.types.LabeledPrice;
import ru.loolzaaa.tgbot4j.core.api.types.Message;
import ru.loolzaaa.tgbot4j.core.api.types.ReplyParameters;

import java.util.List;

/**
 * Use this method to send invoices.
 * On success, the sent {@link Message} is returned.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendInvoice implements TelegramMethod<Message> {
    /**
     * Unique identifier for the target chat or username
     * of the target channel (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Unique identifier for the target message thread (topic)
     * of the forum; for forum supergroups only
     */
    @JsonProperty("message_thread_id")
    private Integer messageThreadId;

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
     * Payment provider token, obtained via @BotFather
     */
    @Required
    @JsonProperty("provider_token")
    private String providerToken;

    /**
     * Three-letter ISO 4217 currency code,
     * see <a href="https://core.telegram.org/bots/payments#supported-currencies">more on currencies</a>
     */
    @Required
    @JsonProperty("currency")
    private String currency;

    /**
     * Price breakdown, a JSON-serialized list of components
     * (e.g. product price, tax, discount, delivery cost,
     * delivery tax, bonus, etc.)
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
     * (2 for the majority of currencies). Defaults to 0
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
     * Unique deep-linking parameter. If left empty,
     * <b>forwarded copies</b> of the sent message
     * will have a Pay button, allowing multiple users
     * to pay directly from the forwarded message,
     * using the same invoice. If non-empty, forwarded copies
     * of the sent message will have a URL button
     * with a deep link to the bot (instead of a Pay button),
     * with the value used as the start parameter
     */
    @JsonProperty("start_parameter")
    private String startParameter;

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
     * for a service. People like it better when they see
     * what they are paying for.
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
     * to complete the order
     */
    @JsonProperty("need_name")
    private Boolean needName;

    /**
     * Pass True if you require the user's phone number
     * to complete the order
     */
    @JsonProperty("need_phone_number")
    private Boolean needPhoneNumber;

    /**
     * Pass True if you require the user's email address
     * to complete the order
     */
    @JsonProperty("need_email")
    private Boolean needEmail;

    /**
     * Pass True if you require the user's shipping address
     * to complete the order
     */
    @JsonProperty("need_shipping_address")
    private Boolean needShippingAddress;

    /**
     * Pass True if the user's phone number should be sent to provider
     */
    @JsonProperty("send_phone_number_to_provider")
    private Boolean sendPhoneNumberToProvider;

    /**
     * Pass True if the user's email address should be sent to provider
     */
    @JsonProperty("send_email_to_provider")
    private Boolean sendEmailToProvider;

    /**
     * Pass True if the final price depends on the shipping method
     */
    @JsonProperty("is_flexible")
    private Boolean isFlexible;

    /**
     * Sends the message <a href="https://telegram.org/blog/channels-2-0#silent-messages">silently</a>. Users
     * will receive a notification with no sound.
     */
    @JsonProperty("disable_notification")
    private Boolean disableNotification;

    /**
     * Protects the contents of the sent message from forwarding and saving
     */
    @JsonProperty("protect_content")
    private Boolean protectContent;

    /**
     * Unique identifier of the message effect to be added
     * to the message; for private chats only
     */
    @JsonProperty("message_effect_id")
    private String messageEffectId;

    /**
     * Description of the message to reply to
     */
    @JsonProperty("reply_parameters")
    private ReplyParameters replyParameters;

    /**
     * A JSON-serialized object for an <a href="https://core.telegram.org/bots/features#inline-keyboards">inline keyboard</a>.
     * If empty, one 'Pay {@code total price}' button will be shown.
     * If not empty, the first button must be a Pay button.
     */
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;

    @Override
    public Message determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Message.class);
    }
}
