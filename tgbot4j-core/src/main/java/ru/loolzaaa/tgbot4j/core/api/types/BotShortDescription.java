package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents the bot's short description.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BotShortDescription {
    /**
     * The bot's short description
     */
    @JsonProperty("short_description")
    private String shortDescription;
}
