package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This object contains information about one member of a chat.
 * Currently, the following 6 types of chat members are supported:
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

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "status")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChatMemberOwner.class, name = "creator"),
        @JsonSubTypes.Type(value = ChatMemberAdministrator.class, name = "administrator"),
        @JsonSubTypes.Type(value = ChatMemberMember.class, name = "member"),
        @JsonSubTypes.Type(value = ChatMemberRestricted.class, name = "restricted"),
        @JsonSubTypes.Type(value = ChatMemberLeft.class, name = "left"),
        @JsonSubTypes.Type(value = ChatMemberBanned.class, name = "kicked"),
})
public interface ChatMember {
}
