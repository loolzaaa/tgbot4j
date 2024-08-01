package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The withdrawal succeeded.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevenueWithdrawalStateSucceeded implements RevenueWithdrawalState {
    /**
     * Type of the state, always “succeeded”
     */
    @JsonProperty("type")
    private String type;

    /**
     * Date the withdrawal was completed in Unix time
     */
    @JsonProperty("date")
    private Integer date;

    /**
     * An HTTPS URL that can be used to see transaction details
     */
    @JsonProperty("url")
    private String url;
}
