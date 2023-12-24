package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a menu button, which opens the bot's list of commands.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuButtonCommands implements MenuButton {
    /**
     * Type of the button, must be commands
     */
    @JsonProperty("type")
    private String type;
}
