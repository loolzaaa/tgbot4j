package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The boost was obtained by subscribing to Telegram Premium
 * or by gifting a Telegram Premium subscription to another user.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatBoostSourcePremium implements ChatBoostSource {
    /**
     * Source of the boost, always “premium”
     */
    @JsonProperty("source")
    private String source;

    /**
     * User that boosted the chat
     */
    @JsonProperty("user")
    private User user;
}
