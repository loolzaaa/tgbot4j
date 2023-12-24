package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the {@link BotCommandScope} of bot commands,
 * covering a specific chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BotCommandScopeChat implements BotCommandScope {
    /**
     * Scope type, must be chat
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
