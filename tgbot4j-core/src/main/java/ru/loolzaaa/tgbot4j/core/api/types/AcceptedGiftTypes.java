package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object describes the types of gifts
 * that can be gifted to a user or a chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcceptedGiftTypes {
    /**
     * True, if unlimited regular gifts are accepted
     */
    @JsonProperty("unlimited_gifts")
    private Boolean unlimitedGifts;

    /**
     * True, if limited regular gifts are accepted
     */
    @JsonProperty("limited_gifts")
    private Boolean limitedGifts;

    /**
     * True, if unique gifts or gifts that
     * can be upgraded to unique for free are accepted
     */
    @JsonProperty("unique_gifts")
    private Boolean uniqueGifts;

    /**
     * True, if a Telegram Premium subscription is accepted
     */
    @JsonProperty("premium_subscription")
    private Boolean premiumSubscription;
}
