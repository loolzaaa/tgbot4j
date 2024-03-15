package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a story.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Story {
    /**
     * Chat that posted the story
     */
    @JsonProperty("chat")
    private Chat chat;

    /**
     * Unique identifier for the story in the chat
     */
    @JsonProperty("id")
    private Long id;
}
