package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This object describes the source of a chat boost.
 * It can be one of
 * <ul>
 *     <li>{@link ChatBoostSourcePremium}</li>
 *     <li>{@link ChatBoostSourceGiftCode}</li>
 *     <li>{@link ChatBoostSourceGiveaway}</li>
 * </ul>
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "source")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChatBoostSourcePremium.class, name = "premium"),
        @JsonSubTypes.Type(value = ChatBoostSourceGiftCode.class, name = "gift_code"),
        @JsonSubTypes.Type(value = ChatBoostSourceGiveaway.class, name = "giveaway"),
})
public interface ChatBoostSource {
}
