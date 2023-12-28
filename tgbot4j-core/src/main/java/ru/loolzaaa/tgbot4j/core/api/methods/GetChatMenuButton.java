package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.MenuButton;

/**
 * Use this method to get the current value of the bot's menu button
 * in a private chat, or the default menu button.
 * Returns {@link MenuButton} on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetChatMenuButton implements TelegramMethod<MenuButton> {
    /**
     * Unique identifier for the target private chat.
     * If not specified, default bot's menu button will be changed
     */
    @JsonProperty("chat_id")
    private Integer chatId;

    @Override
    public MenuButton determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, MenuButton.class);
    }
}
