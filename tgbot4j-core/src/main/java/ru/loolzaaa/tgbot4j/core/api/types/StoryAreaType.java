package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Describes the type of a clickable area on a story.
 * Currently, it can be one of
 * <ul>
 *     <li>{@link StoryAreaTypeLocation}</li>
 *     <li>{@link StoryAreaTypeSuggestedReaction}</li>
 *     <li>{@link StoryAreaTypeLink}</li>
 *     <li>{@link StoryAreaTypeWeather}</li>
 *     <li>{@link StoryAreaTypeUniqueGift}</li>
 * </ul>
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = StoryAreaTypeLocation.class, name = "location"),
        @JsonSubTypes.Type(value = StoryAreaTypeSuggestedReaction.class, name = "suggested_reaction"),
        @JsonSubTypes.Type(value = StoryAreaTypeLink.class, name = "link"),
        @JsonSubTypes.Type(value = StoryAreaTypeWeather.class, name = "weather"),
        @JsonSubTypes.Type(value = StoryAreaTypeUniqueGift.class, name = "unique_gift"),
})
public interface StoryAreaType {
}
