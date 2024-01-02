package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object represents reaction changes
 * on a message with anonymous reactions.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageReactionCountUpdated {
    /**
     * The chat containing the message
     */
    @JsonProperty("chat")
    private Chat chat;

    /**
     * Unique message identifier inside the chat
     */
    @JsonProperty("message_id")
    private Integer messageId;

    /**
     * Date of the change in Unix time
     */
    @JsonProperty("date")
    private Integer date;

    /**
     * List of reactions that are present on the message
     */
    @JsonProperty("reactions")
    private List<ReactionCount> reactions;
}
