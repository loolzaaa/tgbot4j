package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents the bot's name.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BotName {
    /**
     * The bot's name
     */
    @JsonProperty("name")
    private String name;
}
