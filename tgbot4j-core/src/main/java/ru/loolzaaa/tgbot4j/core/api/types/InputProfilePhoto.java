package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This object describes a profile photo to set.
 * Currently, it can be one of
 * <ul>
 *     <li>{@link InputProfilePhotoStatic}</li>
 *     <li>{@link InputProfilePhotoAnimated}</li>
 * </ul>
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = InputProfilePhotoStatic.class, name = "static"),
        @JsonSubTypes.Type(value = InputProfilePhotoAnimated.class, name = "animated"),
})
public interface InputProfilePhoto {
}
