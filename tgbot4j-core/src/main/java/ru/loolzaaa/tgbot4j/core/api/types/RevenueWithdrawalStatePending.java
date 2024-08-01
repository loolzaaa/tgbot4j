package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The withdrawal is in progress.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevenueWithdrawalStatePending implements RevenueWithdrawalState {
    /**
     * Type of the state, always “pending”
     */
    @JsonProperty("type")
    private String type;
}
