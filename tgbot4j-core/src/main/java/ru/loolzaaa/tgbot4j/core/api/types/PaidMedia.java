package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This object describes paid media. Currently, it can be one of
 * <ul>
 *     <li>{@link PaidMediaPreview}</li>
 *     <li>{@link PaidMediaPhoto}</li>
 *     <li>{@link PaidMediaVideo}</li>
 * </ul>
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PaidMediaPreview.class, name = "preview"),
        @JsonSubTypes.Type(value = PaidMediaPhoto.class, name = "photo"),
        @JsonSubTypes.Type(value = PaidMediaVideo.class, name = "video"),
})
public interface PaidMedia {
}
