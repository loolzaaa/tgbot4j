package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a venue. By default, the venue
 * will be sent by the user. Alternatively,
 * you can use input_message_content to send a message
 * with the specified content instead of the venue.
 *
 * @apiNote This will only work in Telegram versions
 * released after 9 April, 2016. Older clients will ignore them.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InlineQueryResultVenue implements InlineQueryResult {
    /**
     * Type of the result, must be venue
     */
    @JsonProperty("type")
    private String type;

    /**
     * Unique identifier for this result, 1-64 Bytes
     */
    @JsonProperty("id")
    private String id;

    /**
     * Latitude of the venue location in degrees
     */
    @JsonProperty("latitude")
    private Double latitude;

    /**
     * Longitude of the venue location in degrees
     */
    @JsonProperty("longitude")
    private Double longitude;

    /**
     * Title of the venue
     */
    @JsonProperty("title")
    private String title;

    /**
     * Address of the venue
     */
    @JsonProperty("address")
    private String address;

    /**
     * Optional. Foursquare identifier of the venue if known
     */
    @JsonProperty("foursquare_id")
    private String foursquareId;

    /**
     * Optional. Foursquare type of the venue, if known.
     * (For example, “arts_entertainment/default”,
     * “arts_entertainment/aquarium” or “food/icecream”.)
     */
    @JsonProperty("foursquare_type")
    private String foursquareType;

    /**
     * Optional. Google Places identifier of the venue
     */
    @JsonProperty("google_place_id")
    private String googlePlaceId;

    /**
     * Optional. Google Places type of the venue. (See <a href="https://developers.google.com/places/web-service/supported_types">supported types</a>.)
     */
    @JsonProperty("google_place_type")
    private String googlePlaceType;

    /**
     * Optional. <a href="https://core.telegram.org/bots/features#inline-keyboards">Inline keyboard</a> attached to the message
     */
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;

    /**
     * Optional. Content of the message to be sent instead of the venue
     */
    @JsonProperty("input_message_content")
    private InputMessageContent inputMessageContent;

    /**
     * Optional. Url of the thumbnail for the result
     */
    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;

    /**
     * Optional. Thumbnail width
     */
    @JsonProperty("thumbnail_width")
    private Integer thumbnailWidth;

    /**
     * Optional. Thumbnail height
     */
    @JsonProperty("thumbnail_height")
    private Integer thumbnailHeight;
}
