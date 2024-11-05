package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents an inline keyboard button
 * that copies specified text to the clipboard.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CopyTextButton {
    /**
     * The text to be copied to the clipboard; 1-256 characters
     */
    @JsonProperty("text")
    private String text;
}
