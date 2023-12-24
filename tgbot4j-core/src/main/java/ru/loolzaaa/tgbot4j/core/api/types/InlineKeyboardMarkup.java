package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object represents an <a href="https://core.telegram.org/bots/features#inline-keyboards">inline keyboard</a>
 * that appears right next to the message it belongs to.
 *
 * @apiNote This will only work in Telegram versions
 * released after 9 April, 2016.
 * Older clients will display unsupported message.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InlineKeyboardMarkup {
    /**
     * Array of button rows, each represented by an Array
     * of {@link InlineKeyboardButton} objects
     */
    @JsonProperty("inline_keyboard")
    private List<List<InlineKeyboardButton>> inlineKeyboard;
}
