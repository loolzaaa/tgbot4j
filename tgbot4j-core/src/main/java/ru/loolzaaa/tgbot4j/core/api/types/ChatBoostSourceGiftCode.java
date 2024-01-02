package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The boost was obtained by the creation of Telegram
 * Premium gift codes to boost a chat. Each such code
 * boosts the chat 4 times for the duration
 * of the corresponding Telegram Premium subscription.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatBoostSourceGiftCode implements ChatBoostSource {
    /**
     * Source of the boost, always “gift_code”
     */
    @JsonProperty("source")
    private String source;

    /**
     * User for which the gift code was created
     */
    @JsonProperty("user")
    private User user;
}
