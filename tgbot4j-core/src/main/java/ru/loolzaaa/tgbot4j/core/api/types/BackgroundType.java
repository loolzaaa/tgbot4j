package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This object describes the type of a background.
 * Currently, it can be one of
 * <ul>
 *     <li>{@link BackgroundTypeFill}</li>
 *     <li>{@link BackgroundTypeWallpaper}</li>
 *     <li>{@link BackgroundTypePattern}</li>
 *     <li>{@link BackgroundTypeChatTheme}</li>
 * </ul>
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BackgroundTypeFill.class, name = "fill"),
        @JsonSubTypes.Type(value = BackgroundTypeWallpaper.class, name = "wallpaper"),
        @JsonSubTypes.Type(value = BackgroundTypePattern.class, name = "pattern"),
        @JsonSubTypes.Type(value = BackgroundTypeChatTheme.class, name = "chat_theme"),
})
public interface BackgroundType {
}
