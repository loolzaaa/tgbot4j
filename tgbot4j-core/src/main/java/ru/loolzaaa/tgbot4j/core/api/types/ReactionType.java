package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This object describes the type of a reaction.
 * Currently, it can be one of
 * <ul>
 *     <li>{@link ReactionTypeEmoji}</li>
 *     <li>{@link ReactionTypeCustomEmoji}</li>
 *     <li>{@link ReactionTypePaid}</li>
 * </ul>
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ReactionTypeEmoji.class, name = "emoji"),
        @JsonSubTypes.Type(value = ReactionTypeCustomEmoji.class, name = "custom_emoji"),
        @JsonSubTypes.Type(value = ReactionTypePaid.class, name = "paid"),
})
public interface ReactionType {
}
