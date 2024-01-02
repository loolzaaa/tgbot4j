package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This object describes the origin of a message.
 * It can be one of
 * <ul>
 *     <li>{@link MessageOriginUser}</li>
 *     <li>{@link MessageOriginHiddenUser}</li>
 *     <li>{@link MessageOriginChat}</li>
 *     <li>{@link MessageOriginChannel}</li>
 * </ul>
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MessageOriginUser.class, name = "user"),
        @JsonSubTypes.Type(value = MessageOriginHiddenUser.class, name = "hidden_user"),
        @JsonSubTypes.Type(value = MessageOriginChat.class, name = "chat"),
        @JsonSubTypes.Type(value = MessageOriginChannel.class, name = "channel"),
})
public interface MessageOrigin {
}
