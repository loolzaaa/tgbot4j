package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a service message
 * about a user boosting a chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatBoostAdded {
    /**
     * Number of boosts added by the user
     */
    @JsonProperty("boost_count")
    private String boostCount;
}
