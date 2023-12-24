package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the {@link InputMessageContent} of a venue message
 * to be sent as the result of an inline query.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputVenueMessageContent {
    /**
     * Latitude of the venue in degrees
     */
    @JsonProperty("latitude")
    private Double latitude;

    /**
     * Longitude of the venue in degrees
     */
    @JsonProperty("longitude")
    private Double longitude;

    /**
     * Name of the venue
     */
    @JsonProperty("title")
    private String title;

    /**
     * Address of the venue
     */
    @JsonProperty("address")
    private String address;

    /**
     * Optional. Foursquare identifier of the venue, if known
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
     * Optional. Google Places type of the venue.
     * (See <a href="https://developers.google.com/places/web-service/supported_types">supported types</a>.)
     */
    @JsonProperty("google_place_type")
    private String googlePlaceType;
}
