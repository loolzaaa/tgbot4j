package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.Message;
import ru.loolzaaa.tgbot4j.core.api.types.ReplyMarkup;
import ru.loolzaaa.tgbot4j.core.api.types.ReplyParameters;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to send point on the map.
 * On success, the sent {@link Message} is returned.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendLocation implements TelegramMethod<Message> {
    /**
     * Unique identifier of the business connection
     * on behalf of which the message will be sent
     */
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    /**
     * Unique identifier for the target chat
     * or username of the target channel
     * (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Unique identifier for the target message
     * thread (topic) of the forum;
     * for forum supergroups only
     */
    @JsonProperty("message_thread_id")
    private Integer messageThreadId;

    /**
     * Latitude of the location
     */
    @Required
    @JsonProperty("latitude")
    private Double latitude;

    /**
     * Longitude of the location
     */
    @Required
    @JsonProperty("longitude")
    private Double longitude;

    /**
     * The radius of uncertainty for the location,
     * measured in meters; 0-1500
     */
    @JsonProperty("horizontal_accuracy")
    private Double horizontalAccuracy;

    /**
     * Period in seconds during which the location
     * will be updated (see <a href="https://telegram.org/blog/live-locations">Live Locations</a>,
     * should be between 60 and 86400, or 0x7FFFFFFF for live locations
     * that can be edited indefinitely.
     */
    @JsonProperty("live_period")
    private Integer livePeriod;

    /**
     * For live locations, a direction in which the user
     * is moving, in degrees. Must be between 1 and 360 if specified.
     */
    @JsonProperty("heading")
    private Integer heading;

    /**
     * For live locations, a maximum distance for proximity
     * alerts about approaching another chat member, in meters.
     * Must be between 1 and 100000 if specified.
     */
    @JsonProperty("proximity_alert_radius")
    private Integer proximityAlertRadius;

    /**
     * Sends the message <a href="https://telegram.org/blog/channels-2-0#silent-messages">silently</a>.
     * Users will receive a notification with no sound.
     */
    @JsonProperty("disable_notification")
    private Boolean disableNotification;

    /**
     * Protects the contents of the sent message
     * from forwarding and saving
     */
    @JsonProperty("protect_content")
    private Boolean protectContent;

    /**
     * Pass True to allow up to 1000 messages per second,
     * ignoring <a href="https://core.telegram.org/bots/faq#how-can-i-message-all-of-my-bot-39s-subscribers-at-once">
     * broadcasting limits</a> for a fee of 0.1 Telegram Stars per message.
     * The relevant Stars will be withdrawn from the bot's balance
     */
    @JsonProperty("allow_paid_broadcast")
    private Boolean allowPaidBroadcast;

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
     * Additional interface options.
     * A JSON-serialized object for an <a href="https://core.telegram.org/bots/features#inline-keyboards">inline keyboard</a>,
     * <a href="https://core.telegram.org/bots/features#keyboards">custom reply keyboard</a>, instructions to remove a reply keyboard
     * or to force a reply from the user.
     */
    @JsonProperty("reply_markup")
    private ReplyMarkup replyMarkup;

    @Override
    public Message determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Message.class);
    }

    @Override
    public void validate() {
        if (chatId == null || chatId.isEmpty()) {
            throw new ApiValidationException("Chat ID parameter can't be null or empty", this);
        }
        if (latitude == null) {
            throw new ApiValidationException("Latitude parameter can't be null or empty", this);
        }
        if (longitude == null) {
            throw new ApiValidationException("Longitude parameter can't be null or empty", this);
        }
        if (horizontalAccuracy != null && (horizontalAccuracy < 0 || horizontalAccuracy > 1500)) {
            throw new ApiValidationException("Horizontal accuracy parameter must be in 0..1500 range", this);
        }
        if (livePeriod != null && (livePeriod < 60 || livePeriod > 86400)) {
            throw new ApiValidationException("Live period parameter must be in 60..86400 range", this);
        }
        if (heading != null && (heading < 1 || heading > 360)) {
            throw new ApiValidationException("Heading parameter must be in 1..360 range", this);
        }
        if (proximityAlertRadius != null && (proximityAlertRadius < 1 || proximityAlertRadius > 100000)) {
            throw new ApiValidationException("Proximity alert radius parameter must be in 1..100000 range", this);
        }
        if (replyMarkup != null) {
            replyMarkup.validate();
        }
    }
}
