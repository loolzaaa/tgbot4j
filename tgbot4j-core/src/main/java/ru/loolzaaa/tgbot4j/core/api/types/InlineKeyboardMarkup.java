package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

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
public class InlineKeyboardMarkup implements ReplyMarkup {
    /**
     * Array of button rows, each represented by an Array
     * of {@link InlineKeyboardButton} objects
     */
    @JsonProperty("inline_keyboard")
    private List<List<InlineKeyboardButton>> inlineKeyboard;

    @Override
    public void validate() {
        if (inlineKeyboard == null || inlineKeyboard.isEmpty()) {
            throw new ApiValidationException("Inline keyboard parameter must not be null or empty", this);
        }
        for (List<InlineKeyboardButton> row : inlineKeyboard) {
            if (row == null || row.isEmpty()) {
                throw new ApiValidationException("Every inline keyboard row must not be null or empty", this);
            }
        }
        for (List<InlineKeyboardButton> row : inlineKeyboard) {
            for (InlineKeyboardButton button : row) {
                button.validate();
            }
        }
    }
}
