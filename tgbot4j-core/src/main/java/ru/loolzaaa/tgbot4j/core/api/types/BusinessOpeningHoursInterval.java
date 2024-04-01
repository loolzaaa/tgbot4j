package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * No description
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessOpeningHoursInterval {
    /**
     * The minute's sequence number in a week,
     * starting on Monday, marking the start
     * of the time interval during which the business
     * is open; 0 - 7 24 60
     */
    @JsonProperty("opening_minute")
    private Integer openingMinute;

    /**
     * The minute's sequence number in a week,
     * starting on Monday, marking the end
     * of the time interval during which the business
     * is open; 0 - 8 24 60
     */
    @JsonProperty("closing_minute")
    private Integer closingMinute;
}
