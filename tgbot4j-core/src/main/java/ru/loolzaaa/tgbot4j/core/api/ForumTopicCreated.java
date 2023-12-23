package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a service message
 * about a new forum topic created in the chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForumTopicCreated {
    /**
     * Name of the topic
     */
    @JsonProperty("name")
    private String name;

    /**
     * Color of the topic icon in RGB format
     */
    @JsonProperty("icon_color")
    private Integer iconColor;

    /**
     * Optional. Unique identifier of the custom emoji shown as the topic icon
     */
    @JsonProperty("icon_custom_emoji_id")
    private String iconCustomEmojiId;
}
