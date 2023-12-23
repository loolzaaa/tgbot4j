package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a service message
 * about an edited forum topic.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForumTopicEdited {
    /**
     * Optional. New name of the topic, if it was edited
     */
    @JsonProperty("name")
    private String name;

    /**
     * Optional. New identifier of the custom emoji shown as the topic icon,
     * if it was edited; an empty string if the icon was removed
     */
    @JsonProperty("icon_custom_emoji_id")
    private String iconCustomEmojiId;
}
