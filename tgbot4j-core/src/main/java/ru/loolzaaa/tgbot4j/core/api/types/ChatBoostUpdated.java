package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a boost added to a chat or changed.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatBoostUpdated {
    /**
     * Chat which was boosted
     */
    @JsonProperty("chat")
    private Chat chat;

    /**
     * Infomation about the chat boost
     */
    @JsonProperty("boost")
    private ChatBoost boost;
}
