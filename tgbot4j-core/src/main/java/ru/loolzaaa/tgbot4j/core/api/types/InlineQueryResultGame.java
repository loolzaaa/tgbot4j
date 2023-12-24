package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a {@link Game}.
 *
 * @apiNote This will only work in Telegram versions
 * released after October 1, 2016. Older clients will not display
 * any inline results if a game result is among them.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InlineQueryResultGame implements InlineQueryResult {
    /**
     * Type of the result, must be game
     */
    @JsonProperty("type")
    private String type;

    /**
     * Unique identifier for this result, 1-64 bytes
     */
    @JsonProperty("id")
    private String id;

    /**
     * Short name of the game
     */
    @JsonProperty("game_short_name")
    private String gameShortName;

    /**
     * Optional. <a href="https://core.telegram.org/bots/features#inline-keyboards">Inline keyboard</a> attached to the message
     */
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;
}
