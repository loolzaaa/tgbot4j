package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.InlineKeyboardMarkup;
import ru.loolzaaa.tgbot4j.core.api.types.Message;

/**
 * Use this method to edit only the reply markup of messages.
 * On success, if the edited message is not an inline message,
 * the edited {@link Message} is returned, otherwise True is returned.
 * <p>
 * Note that business messages that were not sent by the bot
 * and do not contain an inline keyboard can only be edited
 * within <b>48 hours</b> from the time they were sent.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditMessageReplyMarkup implements TelegramMethod<Object> {
    /**
     * Unique identifier of the business connection on behalf
     * of which the message to be edited was sent
     */
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    /**
     * Required if inline_message_id is not specified.
     * Unique identifier for the target chat or username
     * of the target channel (in the format {@code @channelusername})
     */
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Required if inline_message_id is not specified.
     * Identifier of the message to edit
     */
    @JsonProperty("message_id")
    private Integer messageId;

    /**
     * Required if chat_id and message_id are not specified.
     * Identifier of the inline message
     */
    @JsonProperty("inline_message_id")
    private String inlineMessageId;

    /**
     * A JSON-serialized object for an <a hred="https://core.telegram.org/bots/features#inline-keyboards">inline keyboard</a>.
     */
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;

    @Override
    public Object determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        if (resultNode instanceof ObjectNode) {
            return deserializeObjectResponse(mapper, resultNode, Message.class);
        } else if (resultNode instanceof BooleanNode) {
            return deserializeObjectResponse(mapper, resultNode, Boolean.class);
        }
        return null;
    }
}
