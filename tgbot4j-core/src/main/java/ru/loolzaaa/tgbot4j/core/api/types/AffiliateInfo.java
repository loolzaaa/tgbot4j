package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contains information about the affiliate
 * that received a commission via this transaction.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AffiliateInfo {
    /**
     * Optional. The bot or the user that received
     * an affiliate commission if it was received
     * by a bot or a user
     */
    @JsonProperty("affiliate_user")
    private User affiliateUser;

    /**
     * Optional. The chat that received an affiliate commission
     * if it was received by a chat
     */
    @JsonProperty("affiliate_chat")
    private Chat affiliateChat;

    /**
     * The number of Telegram Stars received by the affiliate
     * for each 1000 Telegram Stars received by the bot
     * from referred users
     */
    @JsonProperty("commission_per_mille")
    private Integer commissionPerMille;

    /**
     * Integer amount of Telegram Stars received by the affiliate
     * from the transaction, rounded to 0; can be negative for refunds
     */
    @JsonProperty("amount")
    private Integer amount;

    /**
     * Optional. The number of 1/1000000000 shares of Telegram Stars
     * received by the affiliate; from -999999999 to 999999999;
     * can be negative for refunds
     */
    @JsonProperty("nanostar_amount")
    private Integer nanostarAmount;
}
