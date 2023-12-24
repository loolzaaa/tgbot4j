package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the default {@link BotCommandScope} of bot commands.
 * Default commands are used if no commands with a narrower scope
 * are specified for the user.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BotCommandScopeDefault implements BotCommandScope {
    /**
     * Scope type, must be default
     */
    @JsonProperty("type")
    private String type;
}
