package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object represents a message about the completion
 * of a giveaway with public winners.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiveawayWinners {
    /**
     * The chat that created the giveaway
     */
    @JsonProperty("chat")
    private Chat chat;

    /**
     * Identifier of the messsage with the giveaway in the chat
     */
    @JsonProperty("giveaway_message_id")
    private Integer giveawayMessageId;

    /**
     * Point in time (Unix timestamp) when winners
     * of the giveaway were selected
     */
    @JsonProperty("winners_selection_date")
    private Integer winnersSelectionDate;

    /**
     * Total number of winners in the giveaway
     */
    @JsonProperty("winner_count")
    private Integer winnerCount;

    /**
     * List of up to 100 winners of the giveaway
     */
    @JsonProperty("winners")
    private List<User> winners;

    /**
     * Optional. The number of other chats the user had
     * to join in order to be eligible for the giveaway
     */
    @JsonProperty("additional_chat_count")
    private Integer additionalChatCount;

    /**
     * Optional. The number of Telegram Stars to be split
     * between giveaway winners; for Telegram Star giveaways only
     */
    @JsonProperty("prize_star_count")
    private Integer prizeStarCount;

    /**
     * Optional. The number of months the Telegram Premium
     * subscription won from the giveaway will be active for
     */
    @JsonProperty("premium_subscription_month_count")
    private Integer premiumSubscriptionMonthCount;

    /**
     * Optional. Number of undistributed prizes
     */
    @JsonProperty("unclaimed_prize_count")
    private Integer unclaimedPrizeCount;

    /**
     * Optional. True, if only users who had joined the chats
     * after the giveaway started were eligible to win
     */
    @JsonProperty("only_new_members")
    private Boolean onlyNewMembers;

    /**
     * Optional. True, if the giveaway was canceled
     * because the payment for it was refunded
     */
    @JsonProperty("was_refunded")
    private Boolean wasRefunded;

    /**
     * Optional. Description of additional giveaway prize
     */
    @JsonProperty("prize_description")
    private String prizeDescription;
}
