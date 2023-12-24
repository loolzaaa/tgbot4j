package ru.loolzaaa.tgbot4j.core.api.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents the scope to which bot commands
 * are applied. Currently, the following 7 scopes are supported:
 * <ul>
 *     <li>{@link BotCommandScopeDefault}</li>
 *     <li>{@link BotCommandScopeAllPrivateChats}</li>
 *     <li>{@link BotCommandScopeAllGroupChats}</li>
 *     <li>{@link BotCommandScopeAllChatAdministrators}</li>
 *     <li>{@link BotCommandScopeChat}</li>
 *     <li>{@link BotCommandScopeChatAdministrators}</li>
 *     <li>{@link BotCommandScopeChatMember}</li>
 * </ul>
 * <p>
 * <h3>Determining list of commands</h3>
 * The following algorithm is used to determine the list of commands
 * for a particular user viewing the bot menu.
 * The first list of commands which is set is returned:
 * <p>
 * <h4>Commands in the chat with the bot</h4>
 * <ul>
 *     <li>botCommandScopeChat + language_code</li>
 *     <li>botCommandScopeChat</li>
 *     <li>botCommandScopeAllPrivateChats + language_code</li>
 *     <li>botCommandScopeAllPrivateChats</li>
 *     <li>botCommandScopeDefault + language_code</li>
 *     <li>botCommandScopeDefault</li>
 * </ul>
 * <p>
 * <h4>Commands in group and supergroup chats</h4>
 * <ul>
 *     <li>botCommandScopeChatMember + language_code</li>
 *     <li>botCommandScopeChatMember</li>
 *     <li>botCommandScopeChatAdministrators + language_code (administrators only)</li>
 *     <li>botCommandScopeChatAdministrators (administrators only)</li>
 *     <li>botCommandScopeChat + language_code</li>
 *     <li>botCommandScopeChat</li>
 *     <li>botCommandScopeAllChatAdministrators + language_code (administrators only)</li>
 *     <li>botCommandScopeAllChatAdministrators (administrators only)</li>
 *     <li>botCommandScopeAllGroupChats + language_code</li>
 *     <li>botCommandScopeAllGroupChats</li>
 *     <li>botCommandScopeDefault + language_code</li>
 *     <li>botCommandScopeDefault</li>
 * </ul>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BotCommandScope {
}
