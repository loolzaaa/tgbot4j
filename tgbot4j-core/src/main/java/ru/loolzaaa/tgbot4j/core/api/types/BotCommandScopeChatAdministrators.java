package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the {@link BotCommandScope} of bot commands,
 * covering all administrators of a specific group or supergroup chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BotCommandScopeChatAdministrators {
    /**
     * Scope type, must be chat_administrators
     */
    @JsonProperty("type")
    private String type;

    /**
     * Unique identifier for the target chat or username
     * of the target supergroup (in the format {@code @supergroupusername})
     */
    @JsonProperty("chat_id")
    private String chatId;
}
