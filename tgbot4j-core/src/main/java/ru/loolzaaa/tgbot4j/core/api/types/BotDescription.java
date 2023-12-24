package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents the bot's description.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BotDescription {
    /**
     * The bot's description
     */
    @JsonProperty("description")
    private String description;
}
