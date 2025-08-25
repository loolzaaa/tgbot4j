package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This object describes the content of a story to post.
 * Currently, it can be one of
 * <ul>
 *     <li>{@link InputStoryContentPhoto}</li>
 *     <li>{@link InputStoryContentVideo}</li>
 * </ul>
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = InputStoryContentPhoto.class, name = "photo"),
        @JsonSubTypes.Type(value = InputStoryContentVideo.class, name = "video"),
})
public interface InputStoryContent {
}
