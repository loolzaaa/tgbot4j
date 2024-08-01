package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The withdrawal failed and the transaction was refunded.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevenueWithdrawalStateFailed implements RevenueWithdrawalState {
    /**
     * Type of the state, always “failed”
     */
    @JsonProperty("type")
    private String type;
}
