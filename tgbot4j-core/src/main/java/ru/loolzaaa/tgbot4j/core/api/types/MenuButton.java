package ru.loolzaaa.tgbot4j.core.api.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuButton {
}
