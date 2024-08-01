package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object contains information about one answer
 * option in a poll to be sent.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputPollOption {
    /**
     * Option text, 1-100 characters
     */
    @JsonProperty("text")
    private String text;

    /**
     * Optional. Mode for parsing entities in the text.
     * See <a href="https://core.telegram.org/bots/api#formatting-options">formatting options</a> for more details.
     * Currently, only custom emoji entities are allowed
     */
    @JsonProperty("text_parse_mode")
    private String textParseMode;

    /**
     * Optional. A JSON-serialized list of special entities
     * that appear in the poll option text.
     * It can be specified instead of text_parse_mode
     */
    @JsonProperty("text_entities")
    private List<MessageEntity> textEntities;
}
