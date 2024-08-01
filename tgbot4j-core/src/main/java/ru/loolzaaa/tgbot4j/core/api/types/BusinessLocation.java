package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contains information about the location of a Telegram Business account.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessLocation {
    /**
     * Address of the business
     */
    @JsonProperty("address")
    private String address;

    /**
     * Optional. Location of the business
     */
    @JsonProperty("location")
    private Location location;
}
