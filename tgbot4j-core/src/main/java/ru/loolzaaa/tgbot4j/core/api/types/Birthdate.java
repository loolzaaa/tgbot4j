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
public class Birthdate {
    /**
     * Day of the user's birth; 1-31
     */
    @JsonProperty("day")
    private Integer day;

    /**
     * Month of the user's birth; 1-12
     */
    @JsonProperty("month")
    private Integer month;

    /**
     * Optional. Year of the user's birth
     */
    @JsonProperty("year")
    private Integer year;
}
