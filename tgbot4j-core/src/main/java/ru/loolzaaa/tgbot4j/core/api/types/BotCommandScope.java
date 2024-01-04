package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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
 * <b>Determining list of commands</b>
 * The following algorithm is used to determine the list of commands
 * for a particular user viewing the bot menu.
 * The first list of commands which is set is returned:
 * <p>
 * <b>Commands in the chat with the bot</b>
 * <ul>
 *     <li>botCommandScopeChat + language_code</li>
 *     <li>botCommandScopeChat</li>
 *     <li>botCommandScopeAllPrivateChats + language_code</li>
 *     <li>botCommandScopeAllPrivateChats</li>
 *     <li>botCommandScopeDefault + language_code</li>
 *     <li>botCommandScopeDefault</li>
 * </ul>
 * <p>
 * <b>Commands in group and supergroup chats</b>
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

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BotCommandScopeDefault.class, name = "default"),
        @JsonSubTypes.Type(value = BotCommandScopeAllPrivateChats.class, name = "all_private_chats"),
        @JsonSubTypes.Type(value = BotCommandScopeAllGroupChats.class, name = "all_group_chats"),
        @JsonSubTypes.Type(value = BotCommandScopeAllChatAdministrators.class, name = "all_chat_administrators"),
        @JsonSubTypes.Type(value = BotCommandScopeChat.class, name = "chat"),
        @JsonSubTypes.Type(value = BotCommandScopeChatAdministrators.class, name = "chat_administrators"),
        @JsonSubTypes.Type(value = BotCommandScopeChatMember.class, name = "chat_member"),
})
public interface BotCommandScope {
}
