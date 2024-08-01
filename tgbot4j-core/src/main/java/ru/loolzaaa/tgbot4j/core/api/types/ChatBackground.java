package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a chat background.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatBackground {
    /**
     * Type of the background
     */
    @JsonProperty("type")
    private BackgroundType type;
}
