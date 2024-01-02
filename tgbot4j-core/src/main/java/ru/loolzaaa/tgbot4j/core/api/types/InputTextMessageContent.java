package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents the {@link InputMessageContent} of a text message
 * to be sent as the result of an inline query.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputTextMessageContent implements InputMessageContent {
    /**
     * Text of the message to be sent, 1-4096 characters
     */
    @JsonProperty("message_text")
    private String messageText;

    /**
     * Optional. Mode for parsing entities in the message text.
     * See <a href="https://core.telegram.org/bots/api#formatting-options">formatting options</a> for more details.
     */
    @JsonProperty("parse_mode")
    private String parseMode;

    /**
     * Optional. List of special entities that appear in message text,
     * which can be specified instead of parse_mode
     */
    @JsonProperty("entities")
    private List<MessageEntity> entities;

    /**
     * Optional. Link preview generation options for the message
     */
    @JsonProperty("link_preview_options")
    private LinkPreviewOptions linkPreviewOptions;
}
