package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object contains information about the color scheme
 * for a user's name, message replies and link previews
 * based on a unique gift.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniqueGiftColors {
    /**
     * Custom emoji identifier of the unique gift's model
     */
    @JsonProperty("model_custom_emoji_id")
    private String modelCustomEmojiId;

    /**
     * Custom emoji identifier of the unique gift's symbol
     */
    @JsonProperty("symbol_custom_emoji_id")
    private String symbolCustomEmojiId;

    /**
     * Main color used in light themes; RGB format
     */
    @JsonProperty("light_theme_main_color")
    private Integer lightThemeMainColor;

    /**
     * List of 1-3 additional colors used in light themes; RGB format
     */
    @JsonProperty("light_theme_other_colors")
    private List<Integer> lightThemeOtherColors;

    /**
     * Main color used in dark themes; RGB format
     */
    @JsonProperty("dark_theme_main_color")
    private Integer darkThemeMainColor;

    /**
     * List of 1-3 additional colors used in dark themes; RGB format
     */
    @JsonProperty("dark_theme_other_colors")
    private List<Integer> darkThemeOtherColors;
}
