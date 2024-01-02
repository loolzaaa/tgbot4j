package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a boost removed from a chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatBoostRemoved {
    /**
     * Chat which was boosted
     */
    @JsonProperty("chat")
    private Chat chat;

    /**
     * Unique identifier of the boost
     */
    @JsonProperty("boost_id")
    private String boostId;

    /**
     * Point in time (Unix timestamp) when the boost was removed
     */
    @JsonProperty("remove_date")
    private Integer removeDate;

    /**
     * Source of the removed boost
     */
    @JsonProperty("source")
    private ChatBoostSource source;
}
