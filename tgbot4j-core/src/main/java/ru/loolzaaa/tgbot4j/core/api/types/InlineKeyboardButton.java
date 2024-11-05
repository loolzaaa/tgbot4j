package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Validated;
import ru.loolzaaa.tgbot4j.core.api.methods.AnswerWebAppQuery;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * This object represents one button of an inline keyboard.
 * Exactly one of the optional fields must be used
 * to specify type of the button.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InlineKeyboardButton implements Validated {
    /**
     * Label text on the button
     */
    @JsonProperty("text")
    private String text;

    /**
     * Optional. HTTP or tg:// URL to be opened when the button is pressed.
     * Links {@code tg://user?id=<user_id>} can be used to mention a user
     * by their ID without using a username,
     * if this is allowed by their privacy settings.
     */
    @JsonProperty("url")
    private String url;

    /**
     * Optional. Data to be sent in a {@link CallbackQuery}
     * to the bot when the button is pressed, 1-64 bytes
     */
    @JsonProperty("callback_data")
    private String callbackData;

    /**
     * Optional. Description of the <a href="https://core.telegram.org/bots/webapps">Web</a> App that will be launched
     * when the user presses the button. The Web App will be able to send
     * an arbitrary message on behalf of the user
     * using the method {@link AnswerWebAppQuery}.
     * Available only in private chats between a user and the bot.
     * Not supported for messages sent on behalf
     * of a Telegram Business account.
     */
    @JsonProperty("web_app")
    private WebAppInfo webApp;

    /**
     * Optional. An HTTPS URL used to automatically authorize the user.
     * Can be used as a replacement for the <a href="https://core.telegram.org/widgets/login">Telegram Login Widget</a>.
     */
    @JsonProperty("login_url")
    private LoginUrl loginUrl;

    /**
     * Optional. If set, pressing the button will prompt the user
     * to select one of their chats, open that chat
     * and insert the bot's username and the specified inline query
     * in the input field. May be empty, in which case
     * just the bot's username will be inserted.
     * Not supported for messages sent on behalf
     * of a Telegram Business account.
     */
    @JsonProperty("switch_inline_query")
    private String switchInlineQuery;

    /**
     * Optional. If set, pressing the button will insert the bot's username
     * and the specified inline query in the current chat's input field.
     * May be empty, in which case only the bot's username will be inserted.
     * <p>
     * This offers a quick way for the user to open your bot
     * in inline mode in the same chat - good for selecting
     * something from multiple options.
     * <p>
     * Not supported for messages sent on behalf
     * of a Telegram Business account.
     */
    @JsonProperty("switch_inline_query_current_chat")
    private String switchInlineQueryCurrentChat;

    /**
     * Optional. If set, pressing the button will prompt the user
     * to select one of their chats of the specified type,
     * open that chat and insert the bot's username
     * and the specified inline query in the input field
     * <p>
     * Not supported for messages sent on behalf
     * of a Telegram Business account.
     */
    @JsonProperty("switch_inline_query_chosen_chat")
    private SwitchInlineQueryChosenChat switchInlineQueryChosenChat;

    /**
     * Optional. Description of the button
     * that copies the specified text to the clipboard.
     */
    @JsonProperty("copy_text")
    private CopyTextButton copyText;

    /**
     * Optional. Description of the game that will be launched
     * when the user presses the button.
     *
     * @apiNote This type of button <b>must</b> always
     * be the first button in the first row.
     */
    @JsonProperty("callback_game")
    private CallbackGame callbackGame;

    /**
     * Optional. Specify True, to send a <a href="https://core.telegram.org/bots/api#payments">Pay button</a>.
     * <p>
     * Substrings “⭐” and “XTR” in the buttons's text
     * will be replaced with a Telegram Star icon.
     *
     * @apiNote This type of button <b>must</b> always be the first button
     * in the first row and can only be used in invoice messages.
     */
    @JsonProperty("pay")
    private Boolean pay;

    @Override
    public void validate() {
        if (text == null || text.isEmpty()) {
            throw new ApiValidationException("Text parameter must not be null or empty", this);
        }
    }
}
