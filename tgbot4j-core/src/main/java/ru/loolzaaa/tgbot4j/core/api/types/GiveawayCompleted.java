package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a service message
 * about the completion of a giveaway
 * without public winners.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiveawayCompleted {
    /**
     * Number of winners in the giveaway
     */
    @JsonProperty("winner_count")
    private Integer winnerCount;

    /**
     * Optional. Number of undistributed prizes
     */
    @JsonProperty("unclaimed_prize_count")
    private Integer unclaimedPrizeCount;

    /**
     * Optional. Message with the giveaway that was completed,
     * if it wasn't deleted
     */
    @JsonProperty("giveaway_message")
    private Message giveawayMessage;

    /**
     * Optional. True, if the giveaway is a Telegram Star giveaway.
     * Otherwise, currently, the giveaway is a Telegram Premium giveaway.
     */
    @JsonProperty("is_star_giveaway")
    private Boolean isStarGiveaway;
}
