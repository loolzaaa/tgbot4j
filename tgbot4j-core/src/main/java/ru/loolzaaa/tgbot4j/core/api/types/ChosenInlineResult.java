package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a {@link InlineQueryResult} of an inline query
 * that was chosen by the user
 * and sent to their chat partner.
 *
 * @apiNote It is necessary to enable <a href="https://core.telegram.org/bots/inline#collecting-feedback">inline feedback</a>
 * via @BotFather in order to receive these objects in updates.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChosenInlineResult {
    /**
     * The unique identifier for the result that was chosen
     */
    @JsonProperty("result_id")
    private String resultId;

    /**
     * The user that chose the result
     */
    @JsonProperty("from")
    private User from;

    /**
     * Optional. Sender location, only for bots
     * that require user location
     */
    @JsonProperty("location")
    private Location location;

    /**
     * Optional. Identifier of the sent inline message.
     * Available only if there is an {@link InlineKeyboardMarkup}
     * attached to the message. Will be also received
     * in {@link CallbackQuery} and can be used
     * to <a href="https://core.telegram.org/bots/api#updating-messages">edit</a> the message.
     */
    @JsonProperty("result_id")
    private String inline_message_id;

    /**
     * The query that was used to obtain the result
     */
    @JsonProperty("query")
    private String query;
}
