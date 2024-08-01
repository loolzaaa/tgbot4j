package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a withdrawal transaction with Fragment.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionPartnerFragment implements TransactionPartner {
    /**
     * Type of the transaction partner, always “fragment”
     */
    @JsonProperty("type")
    private String type;

    /**
     * Optional. State of the transaction if the transaction is outgoing
     */
    @JsonProperty("withdrawal_state")
    private RevenueWithdrawalState withdrawalState;
}
