package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This object describes a gift received and owned
 * by a user or a chat. Currently, it can be one of
 * <ul>
 *     <li>{@link OwnedGiftRegular}</li>
 *     <li>{@link OwnedGiftUnique}</li>
 * </ul>
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = OwnedGiftRegular.class, name = "regular"),
        @JsonSubTypes.Type(value = OwnedGiftUnique.class, name = "unique"),
})
public interface OwnedGift {
}
