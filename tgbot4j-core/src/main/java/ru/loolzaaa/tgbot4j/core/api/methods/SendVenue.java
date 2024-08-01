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
 * Use this method to send information about a venue.
 * On success, the sent {@link Message} is returned.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendVenue implements TelegramMethod<Message> {
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
     * Latitude of the venue
     */
    @Required
    @JsonProperty("latitude")
    private Double latitude;

    /**
     * Longitude of the venue
     */
    @Required
    @JsonProperty("longitude")
    private Double longitude;

    /**
     * Name of the venue
     */
    @Required
    @JsonProperty("title")
    private String title;

    /**
     * Address of the venue
     */
    @Required
    @JsonProperty("address")
    private String address;

    /**
     * Foursquare identifier of the venue
     */
    @JsonProperty("foursquare_id")
    private String foursquareId;

    /**
     * Foursquare type of the venue, if known.
     * (For example, “arts_entertainment/default”,
     * “arts_entertainment/aquarium” or “food/icecream”.)
     */
    @JsonProperty("foursquare_type")
    private String foursquareType;

    /**
     * Google Places identifier of the venue
     */
    @JsonProperty("google_place_id")
    private String googlePlaceId;

    /**
     * Google Places type of the venue.
     * (See <a href="https://developers.google.com/places/web-service/supported_types">supported types</a>.)
     */
    @JsonProperty("google_place_type")
    private String googlePlaceType;

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
        if (title == null) {
            throw new ApiValidationException("Title parameter can't be null or empty", this);
        }
        if (address == null) {
            throw new ApiValidationException("Address parameter can't be null or empty", this);
        }
        if (replyMarkup != null) {
            replyMarkup.validate();
        }
    }
}
