package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a venue.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venue {
    /**
     * Venue location. Can't be a live location
     */
    @JsonProperty("location")
    private Location location;

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
     * Optional. Foursquare identifier of the venue
     */
    @JsonProperty("foursquare_id")
    private String foursquareId;

    /**
     * Optional. Foursquare type of the venue.
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
}
