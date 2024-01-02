package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object contains information about the quoted part
 * of a message that is replied to by the given message.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextQuote {
    /**
     * Text of the quoted part of a message
     * that is replied to by the given message
     */
    @JsonProperty("text")
    private String text;

    /**
     * Optional. Special entities that appear in the quote.
     * Currently, only bold, italic, underline, strikethrough,
     * spoiler, and custom_emoji entities are kept in quotes.
     */
    @JsonProperty("entities")
    private List<MessageEntity> entities;

    /**
     * Approximate quote position in the original message
     * in UTF-16 code units as specified by the sender
     */
    @JsonProperty("position")
    private Integer position;

    /**
     * Optional. True, if the quote was chosen manually
     * by the message sender. Otherwise, the quote
     * was added automatically by the server.
     */
    @JsonProperty("is_manual")
    private Boolean isManual;
}
