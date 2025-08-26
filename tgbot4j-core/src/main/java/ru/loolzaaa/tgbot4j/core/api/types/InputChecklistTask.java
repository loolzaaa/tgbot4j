package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Describes a task to add to a checklist.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputChecklistTask {
    /**
     * Unique identifier of the task; must be positive
     * and unique among all task identifiers currently present
     * in the checklist
     */
    @JsonProperty("id")
    private Integer id;

    /**
     * Text of the task; 1-100 characters after entities parsing
     */
    @JsonProperty("text")
    private String text;

    /**
     * Optional. Mode for parsing entities in the text.
     * See <a href="https://core.telegram.org/bots/api#formatting-options">formatting options</a> for more details.
     */
    @JsonProperty("parse_mode")
    private String parseMode;

    /**
     * Optional. List of special entities that appear in the text,
     * which can be specified instead of parse_mode. Currently,
     * only bold, italic, underline, strikethrough, spoiler,
     * and custom_emoji entities are allowed.
     */
    @JsonProperty("text_entities")
    private List<MessageEntity> textEntities;
}
