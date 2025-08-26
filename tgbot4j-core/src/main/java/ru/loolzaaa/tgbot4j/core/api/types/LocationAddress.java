package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes the physical address of a location.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationAddress {
    /**
     * The two-letter ISO 3166-1 alpha-2 country code
     * of the country where the location is located
     */
    @JsonProperty("country_code")
    private String countryCode;

    /**
     * Optional. State of the location
     */
    @JsonProperty("state")
    private String state;

    /**
     * Optional. City of the location
     */
    @JsonProperty("city")
    private String city;

    /**
     * Optional. Street address of the location
     */
    @JsonProperty("street")
    private String street;
}
