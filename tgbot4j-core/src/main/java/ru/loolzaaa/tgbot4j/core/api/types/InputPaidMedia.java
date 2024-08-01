package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This object describes the paid media to be sent.
 * Currently, it can be one of
 * <ul>
 *     <li>{@link InputPaidMediaPhoto}</li>
 *     <li>{@link InputPaidMediaVideo}</li>
 * </ul>
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = InputPaidMediaPhoto.class, name = "photo"),
        @JsonSubTypes.Type(value = InputPaidMediaVideo.class, name = "video"),
})
public interface InputPaidMedia {
}
