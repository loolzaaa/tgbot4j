package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The background is taken directly from a built-in chat theme.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BackgroundTypeChatTheme implements BackgroundType {
    /**
     * Type of the background, always “chat_theme”
     */
    @JsonProperty("type")
    private String type;

    /**
     * Name of the chat theme, which is usually an emoji
     */
    @JsonProperty("theme_name")
    private String themeName;
}
