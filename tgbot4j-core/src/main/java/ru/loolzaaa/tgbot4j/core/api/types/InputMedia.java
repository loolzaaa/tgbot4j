package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This object represents the content of a media message
 * to be sent. It should be one of
 * <ul>
 *     <li>{@link InputMediaAnimation}</li>
 *     <li>{@link InputMediaDocument}</li>
 *     <li>{@link InputMediaAudio}</li>
 *     <li>{@link InputMediaPhoto}</li>
 *     <li>{@link InputMediaVideo}</li>
 * </ul>
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = InputMediaAnimation.class, name = "animation"),
        @JsonSubTypes.Type(value = InputMediaDocument.class, name = "document"),
        @JsonSubTypes.Type(value = InputMediaAudio.class, name = "audio"),
        @JsonSubTypes.Type(value = InputMediaPhoto.class, name = "photo"),
        @JsonSubTypes.Type(value = InputMediaVideo.class, name = "video"),
})
public interface InputMedia {
}
