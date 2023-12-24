package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the {@link BotCommandScope} of bot commands,
 * covering all group and supergroup chats.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BotCommandScopeAllGroupChats implements BotCommandScope {
    /**
     * Scope type, must be all_group_chats
     */
    @JsonProperty("type")
    private String type;
}
