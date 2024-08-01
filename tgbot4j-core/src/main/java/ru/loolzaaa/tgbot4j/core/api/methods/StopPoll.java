package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.InlineKeyboardMarkup;
import ru.loolzaaa.tgbot4j.core.api.types.Poll;

/**
 * Use this method to stop a poll which
 * was sent by the bot. On success,
 * the stopped {@link Poll} is returned.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StopPoll implements TelegramMethod<Poll> {
    /**
     * Unique identifier of the business connection on behalf
     * of which the message to be edited was sent
     */
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    /**
     * Unique identifier for the target chat or username
     * of the target channel (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Identifier of the original message with the poll
     */
    @Required
    @JsonProperty("message_id")
    private Integer messageId;

    /**
     * A JSON-serialized object for an <a hred="https://core.telegram.org/bots/features#inline-keyboards">inline keyboard</a>.
     */
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;

    @Override
    public Poll determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Poll.class);
    }
}
