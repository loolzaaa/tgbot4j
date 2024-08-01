package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the {@link InputMessageContent} of a location message
 * to be sent as the result of an inline query.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputLocationMessageContent implements InputMessageContent {
    /**
     * Latitude of the location in degrees
     */
    @JsonProperty("latitude")
    private Double latitude;

    /**
     * Longitude of the location in degrees
     */
    @JsonProperty("longitude")
    private Double longitude;

    /**
     * Optional. The radius of uncertainty for the location,
     * measured in meters; 0-1500
     */
    @JsonProperty("horizontal_accuracy")
    private Double horizontalAccuracy;

    /**
     * Optional. Period in seconds during which the location
     * can be updated, should be between 60 and 86400,
     * or 0x7FFFFFFF for live locations that can be edited indefinitely.
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
}
