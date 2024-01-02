package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object contains information about a chat boost.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatBoost {
    /**
     * Unique identifier of the boost
     */
    @JsonProperty("boost_id")
    private String boostId;

    /**
     * Point in time (Unix timestamp) when the chat was boosted
     */
    @JsonProperty("add_date")
    private Integer addDate;

    /**
     * Point in time (Unix timestamp) when the boost
     * will automatically expire, unless the booster's
     * Telegram Premium subscription is prolonged
     */
    @JsonProperty("expiration_date")
    private Integer expirationDate;

    /**
     * Source of the added boost
     */
    @JsonProperty("source")
    private ChatBoostSource source;
}
