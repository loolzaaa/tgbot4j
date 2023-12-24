package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This object describes the bot's menu button in a private chat.
 * It should be one of
 * <ul>
 *     <li>{@link MenuButtonCommands}</li>
 *     <li>{@link MenuButtonWebApp}</li>
 *     <li>{@link MenuButtonDefault}</li>
 * </ul>
 * If a menu button other than {@link MenuButtonDefault} is set for a private chat,
 * then it is applied in the chat. Otherwise the default menu button is applied.
 * By default, the menu button opens the list of bot commands.
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MenuButtonCommands.class, name = "commands"),
        @JsonSubTypes.Type(value = MenuButtonWebApp.class, name = "web_app"),
        @JsonSubTypes.Type(value = MenuButtonDefault.class, name = "default"),
})
public interface MenuButton {
}
