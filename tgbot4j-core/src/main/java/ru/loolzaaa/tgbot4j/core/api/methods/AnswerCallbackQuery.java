package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.Game;
import ru.loolzaaa.tgbot4j.core.api.types.InlineKeyboardButton;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to send answers to callback queries sent
 * from <a href="https://core.telegram.org/bots/features#inline-keyboards">inline keyboards</a>.
 * The answer will be displayed to the user as a notification
 * at the top of the chat screen or as an alert.
 * On success, True is returned.
 *
 * @implNote Alternatively, the user can be redirected to the specified Game URL.
 * For this option to work, you must first create a game for
 * your bot via @BotFather and accept the terms.
 * Otherwise, you may use links like {@code t.me/your_bot?start=XXXX}
 * that open your bot with a parameter.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerCallbackQuery implements TelegramMethod<Boolean> {
    /**
     * 	Unique identifier for the query to be answered
     */
    @Required
    @JsonProperty("callback_query_id")
    private String callbackQueryId;

    /**
     * Text of the notification. If not specified,
     * nothing will be shown to the user, 0-200 characters
     */
    @JsonProperty("text")
    private String text;

    /**
     * If True, an alert will be shown by the client
     * instead of a notification at the top of the chat screen.
     * Defaults to false.
     */
    @JsonProperty("show_alert")
    private Boolean showAlert;

    /**
     * URL that will be opened by the user's client.
     * If you have created a {@link Game} and accepted the conditions via @BotFather,
     * specify the URL that opens your game - note that this will only work
     * if the query comes from a {@link InlineKeyboardButton}.
     * <p>
     * Otherwise, you may use links like {@code t.me/your_bot?start=XXXX}
     * that open your bot with a parameter.
     */
    @JsonProperty("url")
    private String url;

    /**
     * The maximum amount of time in seconds that the result
     * of the callback query may be cached client-side.
     * Telegram apps will support caching starting in version 3.14.
     * Defaults to 0.
     */
    @JsonProperty("cache_time")
    private Integer cacheTime;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (callbackQueryId == null || callbackQueryId.isEmpty()) {
            throw new ApiValidationException("CallbackQueryId parameter can't be null or empty", this);
        }
        if (text != null && text.length() > 200) {
            throw new ApiValidationException("Text parameter can't have size greater than 200 characters", this);
        }
    }
}
