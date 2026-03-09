package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object describes the rating of a user
 * based on their Telegram Star spendings.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRating {
    /**
     * Current level of the user, indicating their reliability
     * when purchasing digital goods and services.
     * A higher level suggests a more trustworthy customer;
     * a negative level is likely reason for concern.
     */
    @JsonProperty("level")
    private Integer level;

    /**
     * Numerical value of the user's rating;
     * the higher the rating, the better
     */
    @JsonProperty("rating")
    private Integer rating;

    /**
     * The rating value required to get the current level
     */
    @JsonProperty("current_level_rating")
    private Integer currentLevelRating;

    /**
     * The rating value required to get to the next level;
     * omitted if the maximum level was reached
     */
    @JsonProperty("next_level_rating")
    private Integer nextLevelRating;
}
