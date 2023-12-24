package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a shipping address.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingAddress {
    /**
     * Two-letter ISO 3166-1 alpha-2 country code
     */
    @JsonProperty("country_code")
    private String countryCode;

    /**
     * State, if applicable
     */
    @JsonProperty("state")
    private String state;

    /**
     * City
     */
    @JsonProperty("city")
    private String city;

    /**
     * First line for the address
     */
    @JsonProperty("street_line1")
    private String streetLine1;

    /**
     * Second line for the address
     */
    @JsonProperty("street_line2")
    private String streetLine2;

    /**
     * Address post code
     */
    @JsonProperty("post_code")
    private String postCode;
}
