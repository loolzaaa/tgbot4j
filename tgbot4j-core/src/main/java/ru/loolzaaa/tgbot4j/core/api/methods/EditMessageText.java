package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.InlineKeyboardMarkup;
import ru.loolzaaa.tgbot4j.core.api.types.LinkPreviewOptions;
import ru.loolzaaa.tgbot4j.core.api.types.Message;
import ru.loolzaaa.tgbot4j.core.api.types.MessageEntity;

import java.util.List;

/**
 * Use this method to edit text and <a href="https://core.telegram.org/bots/api#games">game</a> messages.
 * On success, if the edited message is not an inline message,
 * the edited {@link ru.loolzaaa.tgbot4j.core.api.types.Message} is returned,
 * otherwise True is returned.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditMessageText implements TelegramMethod<Object> {
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
     * New text of the message, 1-4096 characters after entities parsing
     */
    @Required
    @JsonProperty("text")
    private String text;

    /**
     * Mode for parsing entities in the message text.
     * See <a href="https://core.telegram.org/bots/api#formatting-options">formatting options</a> for more details.
     */
    @JsonProperty("parse_mode")
    private String parseMode;

    /**
     * A JSON-serialized list of special entities
     * that appear in message text, which can be specified
     * instead of parse_mode
     */
    @JsonProperty("entities")
    private List<MessageEntity> entities;

    /**
     * Link preview generation options for the message
     */
    @JsonProperty("link_preview_options")
    private LinkPreviewOptions linkPreviewOptions;

    /**
     * A JSON-serialized object for an <a hred="https://core.telegram.org/bots/features#inline-keyboards">inline keyboard</a>.
     */
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;

    @Override
    public Object determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        if (resultNode instanceof ObjectNode) {
            return deserializeObjectResponse(mapper, resultNode, Message.class);
        } else if (resultNode instanceof BooleanNode){
            return deserializeObjectResponse(mapper, resultNode, Boolean.class);
        }
        return null;
    }
}
