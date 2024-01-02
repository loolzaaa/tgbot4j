package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object describes a message that
 * was deleted or is otherwise inaccessible to the bot.
 */

@JsonDeserialize // Prevent stack overflow
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InaccessibleMessage implements MaybeInaccessibleMessage {
    /**
     * Chat the message belonged to
     */
    @JsonProperty("chat")
    private Chat chat;

    /**
     * Unique message identifier inside the chat
     */
    @JsonProperty("message_id")
    private Integer messageId;

    /**
     * Always 0. The field can be used to differentiate
     * regular and inaccessible messages.
     */
    @JsonProperty("date")
    private Integer date;
}
