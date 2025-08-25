package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a Telegram Star transaction.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StarTransaction {
    /**
     * Unique identifier of the transaction.
     * Coincides with the identifier of the original transaction
     * for refund transactions.
     * Coincides with SuccessfulPayment.telegram_payment_charge_id
     * for successful incoming payments from users.
     */
    @JsonProperty("id")
    private String id;

    /**
     * Number of Telegram Stars transferred by the transaction
     */
    @JsonProperty("amount")
    private Integer amount;

    /**
     * Optional. The number of 1/1000000000 shares
     * of Telegram Stars transferred by the transaction;
     * from 0 to 999999999
     */
    @JsonProperty("nanostar_amount")
    private Integer nanostarAmount;

    /**
     * Date the transaction was created in Unix time
     */
    @JsonProperty("date")
    private Integer date;

    /**
     * Optional. Source of an incoming transaction
     * (e.g., a user purchasing goods or services,
     * Fragment refunding a failed withdrawal).
     * Only for incoming transactions
     */
    @JsonProperty("source")
    private TransactionPartner source;

    /**
     * Optional. Receiver of an outgoing transaction
     * (e.g., a user for a purchase refund,
     * Fragment for a withdrawal).
     * Only for outgoing transactions
     */
    @JsonProperty("receiver")
    private TransactionPartner receiver;
}
