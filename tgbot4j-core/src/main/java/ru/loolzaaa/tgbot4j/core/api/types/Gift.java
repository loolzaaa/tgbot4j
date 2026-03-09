package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a gift that can be sent by the bot.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gift {
    /**
     * Unique identifier of the gift
     */
    @JsonProperty("id")
    private String id;

    /**
     * The sticker that represents the gift
     */
    @JsonProperty("sticker")
    private Sticker sticker;

    /**
     * The number of Telegram Stars that must be paid
     * to send the sticker
     */
    @JsonProperty("star_count")
    private Integer starCount;

    /**
     * Optional. The number of Telegram Stars that must be paid
     * to upgrade the gift to a unique one
     */
    @JsonProperty("upgrade_star_count")
    private Integer upgradeStarCount;

    /**
     * Optional. True, if the gift can only be purchased
     * by Telegram Premium subscribers
     */
    @JsonProperty("is_premium")
    private Boolean isPremium;

    /**
     * Optional. True, if the gift can be used (after being upgraded)
     * to customize a user's appearance
     */
    @JsonProperty("has_colors")
    private Boolean hasColors;

    /**
     * Optional. The total number of gifts of this type
     * that can be sent by all users; for limited gifts only
     */
    @JsonProperty("total_count")
    private Integer totalCount;

    /**
     * Optional. The number of remaining gifts of this type
     * that can be sent; for limited gifts only
     */
    @JsonProperty("remaining_count")
    private Integer remainingCount;

    /**
     * Optional. The total number of gifts of this type
     * that can be sent by the bot; for limited gifts only
     */
    @JsonProperty("personal_total_count")
    private Integer personalTotalCount;

    /**
     * Optional. The number of remaining gifts of this type
     * that can be sent by the bot; for limited gifts only
     */
    @JsonProperty("personal_remaining_count")
    private Integer personalRemainingCount;

    /**
     * Optional. Background of the gift
     */
    @JsonProperty("background")
    private GiftBackground background;

    /**
     * Optional. The total number of different unique gifts
     * that can be obtained by upgrading the gift
     */
    @JsonProperty("unique_gift_variant_count")
    private Integer uniqueGiftVariantCount;

    /**
     * 	Optional. Information about the chat that published the gift
     */
    @JsonProperty("publisher_chat")
    private Chat publisherChat;
}
