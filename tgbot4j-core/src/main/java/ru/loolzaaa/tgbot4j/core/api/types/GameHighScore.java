package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents one row of the high scores table for a game.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameHighScore {
    /**
     * Position in high score table for the game
     */
    @JsonProperty("position")
    private Integer position;

    /**
     * User
     */
    @JsonProperty("user")
    private User user;

    /**
     * Score
     */
    @JsonProperty("score")
    private Integer score;
}
