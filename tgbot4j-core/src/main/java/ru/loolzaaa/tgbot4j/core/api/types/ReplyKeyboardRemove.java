package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Upon receiving a message with this object,
 * Telegram clients will remove the current custom keyboard
 * and display the default letter-keyboard.
 * By default, custom keyboards are displayed
 * until a new keyboard is sent by a bot.
 * An exception is made for one-time keyboards
 * that are hidden immediately after the user presses
 * a button (see {@link ReplyKeyboardMarkup}).
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyKeyboardRemove implements ReplyMarkup {
    /**
     * Requests clients to remove the custom keyboard
     * (user will not be able to summon this keyboard;
     * if you want to hide the keyboard from sight
     * but keep it accessible, use one_time_keyboard
     * in {@link ReplyKeyboardMarkup})
     */
    @JsonProperty("remove_keyboard")
    private Boolean removeKeyboard;

    /**
     * Optional. Use this parameter if you want to remove
     * the keyboard for specific users only.
     * Targets: 1) users that are @mentioned in the text
     * of the {@link Message} object; 2) if the bot's message is a reply
     * (has reply_to_message_id), sender of the original message.
     * <p>
     * Example: A user votes in a poll, bot returns confirmation message
     * in reply to the vote and removes the keyboard for that user,
     * while still showing the keyboard with poll options
     * to users who haven't voted yet.
     */
    @JsonProperty("selective")
    private Boolean selective;

    @Override
    public void validate() {
        if (removeKeyboard == null) {
            throw new ApiValidationException("Remove keyboard parameter must not be null", this);
        }
    }
}
