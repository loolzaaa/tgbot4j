package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a bot command.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BotCommand {
    /**
     * Text of the command; 1-32 characters.
     * Can contain only lowercase English letters,
     * digits and underscores.
     */
    @JsonProperty("command")
    private String command;

    /**
     * Description of the command; 1-256 characters.
     */
    @JsonProperty("description")
    private String description;
}
