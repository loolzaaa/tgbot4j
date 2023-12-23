package ru.loolzaaa.tgbot4j.core.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object contains information about one member of a chat. Currently, the following 6 types of chat members are supported:
 *
 * <ul>
 *     <li>{@link ChatMemberOwner}</li>
 *     <li>{@link ChatMemberAdministrator}</li>
 *     <li>{@link ChatMemberMember}</li>
 *     <li>{@link ChatMemberRestricted}</li>
 *     <li>{@link ChatMemberLeft}</li>
 *     <li>{@link ChatMemberBanned}</li>
 * </ul>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMember {
}
