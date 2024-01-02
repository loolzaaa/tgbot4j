package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a location on a map. By default, the location
 * will be sent by the user. Alternatively, you can use input_message_content
 * to send a message with the specified content instead of the location.
 *
 * @apiNote This will only work in Telegram versions
 * released after 9 April, 2016. Older clients will ignore them.
 */

@JsonDeserialize // Prevent stack overflow
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InlineQueryResultLocation implements InlineQueryResult {
    /**
     * Type of the result, must be location
     */
    @JsonProperty("type")
    private String type;

    /**
     * Unique identifier for this result, 1-64 Bytes
     */
    @JsonProperty("id")
    private String id;

    /**
     * Location latitude in degrees
     */
    @JsonProperty("latitude")
    private Double latitude;

    /**
     * Location longitude in degrees
     */
    @JsonProperty("longitude")
    private Double longitude;

    /**
     * Location title
     */
    @JsonProperty("title")
    private String title;

    /**
     * Optional. The radius of uncertainty for the location,
     * measured in meters; 0-1500
     */
    @JsonProperty("horizontal_accuracy")
    private Double horizontalAccuracy;

    /**
     * Optional. Period in seconds for which the location
     * can be updated, should be between 60 and 86400.
     */
    @JsonProperty("live_period")
    private Integer livePeriod;

    /**
     * Optional. For live locations, a direction in which the user
     * is moving, in degrees. Must be between 1 and 360 if specified.
     */
    @JsonProperty("heading")
    private Integer heading;

    /**
     * Optional. For live locations, a maximum distance for proximity alerts
     * about approaching another chat member, in meters.
     * Must be between 1 and 100000 if specified.
     */
    @JsonProperty("proximity_alert_radius")
    private Integer proximityAlertRadius;

    /**
     * Optional. <a href="https://core.telegram.org/bots/features#inline-keyboards">Inline keyboard</a> attached to the message
     */
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;

    /**
     * Optional. Content of the message to be sent instead of the location
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
