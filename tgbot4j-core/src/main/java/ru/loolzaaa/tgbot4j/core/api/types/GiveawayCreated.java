package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a service message
 * about the creation of a scheduled giveaway.
 * Currently holds no information.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiveawayCreated {
    /**
     * Optional. The number of Telegram Stars to be split
     * between giveaway winners; for Telegram Star giveaways only
     */
    @JsonProperty("prize_star_count")
    private Integer prizeStarCount;
}
