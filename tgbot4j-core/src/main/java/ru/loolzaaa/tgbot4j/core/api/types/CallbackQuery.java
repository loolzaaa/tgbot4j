package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.methods.AnswerCallbackQuery;

/**
 * This object represents an incoming callback query
 * from a callback button in an <a href="https://core.telegram.org/bots/features#inline-keyboards">inline keyboard</a>.
 * If the button that originated the query was attached
 * to a message sent by the bot, the field message will be present.
 * If the button was attached to a message sent via the bot (in <a href="https://core.telegram.org/bots/api#inline-mode">inline mode</a>),
 * the field inline_message_id will be present.
 * Exactly one of the fields data or game_short_name will be present.
 *
 * @apiNote After the user presses a callback button,
 * Telegram clients will display a progress bar
 * until you call {@link AnswerCallbackQuery}. It is, therefore,
 * necessary to react by calling {@link AnswerCallbackQuery}
 * even if no notification to the user is needed
 * (e.g., without specifying any of the optional parameters).
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallbackQuery {
    /**
     * Unique identifier for this query
     */
    @JsonProperty("id")
    private String id;

    /**
     * Sender
     */
    @JsonProperty("from")
    private User from;

    /**
     * Optional. Message sent by the bot with the callback button
     * that originated the query
     */
    @JsonProperty("message")
    private MaybeInaccessibleMessage message;

    /**
     * Optional. Identifier of the message sent via the bot
     * in inline mode, that originated the query.
     */
    @JsonProperty("inline_message_id")
    private String inlineMessageId;

    /**
     * Global identifier, uniquely corresponding to the chat
     * to which the message with the callback button was sent.
     * Useful for high scores in <a href="https://core.telegram.org/bots/api#games">games</a>.
     */
    @JsonProperty("chat_instance")
    private String chatInstance;

    /**
     * Optional. Data associated with the callback button.
     * Be aware that the message originated the query
     * can contain no callback buttons with this data.
     */
    @JsonProperty("data")
    private String data;

    /**
     * Optional. Short name of a <a href="https://core.telegram.org/bots/api#games">Game</a> to be returned,
     * serves as the unique identifier for the game
     */
    @JsonProperty("game_short_name")
    private String gameShortName;
}
