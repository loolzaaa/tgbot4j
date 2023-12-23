package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents an animated emoji that displays a random value.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dice {
    /**
     * Emoji on which the dice throw animation is based
     */
    @JsonProperty("emoji")
    private String emoji;

    /**
     * Value of the dice, 1-6 for “🎲”, “🎯” and “🎳” base emoji, 1-5 for “🏀” and “⚽” base emoji, 1-64 for “🎰” base emoji
     */
    @JsonProperty("value")
    private Integer value;
}
