package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.MenuButton;
import ru.loolzaaa.tgbot4j.core.api.types.MenuButtonDefault;

/**
 * Use this method to change the bot's menu button in a private chat,
 * or the default menu button.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetChatMenuButton implements TelegramMethod<Boolean> {
    /**
     * Unique identifier for the target private chat.
     * If not specified, default bot's menu button will be changed
     */
    @JsonProperty("chat_id")
    private Integer chatId;

    /**
     * A JSON-serialized object for the bot's new menu button.
     * Defaults to {@link MenuButtonDefault}
     */
    @JsonProperty("menu_button")
    private MenuButton menuButton;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}
