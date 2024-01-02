package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The boost was obtained by the creation of a Telegram Premium giveaway.
 * This boosts the chat 4 times for the duration of the corresponding
 * Telegram Premium subscription.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatBoostSourceGiveaway implements ChatBoostSource {
    /**
     * Source of the boost, always “giveaway”
     */
    @JsonProperty("source")
    private String source;

    /**
     * Identifier of a message in the chat with the giveaway;
     * the message could have been deleted already.
     * May be 0 if the message isn't sent yet.
     */
    @JsonProperty("giveaway_message_id")
    private Integer giveawayMessageId;

    /**
     * Optional. User that won the prize in the giveaway if any
     */
    @JsonProperty("user")
    private User user;

    /**
     * Optional. True, if the giveaway was completed,
     * but there was no user to win the prize
     */
    @JsonProperty("is_unclaimed")
    private Boolean isUnclaimed;
}
