package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.InlineKeyboardMarkup;
import ru.loolzaaa.tgbot4j.core.api.types.Message;

/**
 * Use this method to edit live location messages.
 * A location can be edited until its live_period expires
 * or editing is explicitly disabled by a call
 * to {@link StopMessageLiveLocation}. On success, if the edited message
 * is not an inline message, the edited {@link Message} is returned,
 * otherwise True is returned.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditMessageLiveLocation implements TelegramMethod<Object> {
    /**
     * Unique identifier of the business connection on behalf
     * of which the message to be edited was sent
     */
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    /**
     * Required if inline_message_id is not specified.
     * Unique identifier for the target chat or username
     * of the target channel (in the format {@code @channelusername})
     */
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Required if inline_message_id is not specified.
     * Identifier of the message to edit
     */
    @JsonProperty("message_id")
    private Integer messageId;

    /**
     * Required if chat_id and message_id are not specified.
     * Identifier of the inline message
     */
    @JsonProperty("inline_message_id")
    private String inlineMessageId;

    /**
     * Latitude of new location
     */
    @Required
    @JsonProperty("latitude")
    private Double latitude;

    /**
     * Longitude of new location
     */
    @Required
    @JsonProperty("longitude")
    private Double longitude;

    /**
     * New period in seconds during which the location can be updated,
     * starting from the message send date. If 0x7FFFFFFF is specified,
     * then the location can be updated forever.
     * Otherwise, the new value must not exceed the current live_period
     * by more than a day, and the live location expiration date
     * must remain within the next 90 days.
     * If not specified, then live_period remains unchanged
     */
    @JsonProperty("live_period")
    private Integer livePeriod;

    /**
     * The radius of uncertainty for the location,
     * measured in meters; 0-1500
     */
    @JsonProperty("horizontal_accuracy")
    private Double horizontalAccuracy;

    /**
     * Direction in which the user is moving, in degrees.
     * Must be between 1 and 360 if specified.
     */
    @JsonProperty("heading")
    private Integer heading;

    /**
     * The maximum distance for proximity alerts
     * about approaching another chat member, in meters.
     * Must be between 1 and 100000 if specified.
     */
    @JsonProperty("proximity_alert_radius")
    private Integer proximityAlertRadius;

    /**
     * A JSON-serialized object for an <a hred="https://core.telegram.org/bots/features#inline-keyboards">inline keyboard</a>.
     */
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;

    @Override
    public Object determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        if (resultNode instanceof ObjectNode) {
            return deserializeObjectResponse(mapper, resultNode, Message.class);
        } else if (resultNode instanceof BooleanNode){
            return deserializeObjectResponse(mapper, resultNode, Boolean.class);
        }
        return null;
    }
}
