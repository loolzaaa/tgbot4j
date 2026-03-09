package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.MessageEntity;

import java.util.List;

/**
 * Use this method to stream a partial message to a user
 * while the message is being generated. Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageDraft implements TelegramMethod<Boolean> {
    /**
     * Unique identifier for the target private chat
     */
    @Required
    @JsonProperty("chat_id")
    private Integer chatId;

    /**
     * Unique identifier for the target message thread
     */
    @JsonProperty("message_thread_id")
    private Integer messageThreadId;

    /**
     * Unique identifier of the message draft; must be non-zero.
     * Changes of drafts with the same identifier are animated
     */
    @Required
    @JsonProperty("draft_id")
    private Integer draftId;

    /**
     * Text of the message to be sent, 1-4096 characters
     * after entities parsing
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
     * that appear in message text,
     * which can be specified instead of parse_mode
     */
    @JsonProperty("entities")
    private List<MessageEntity> entities;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}
