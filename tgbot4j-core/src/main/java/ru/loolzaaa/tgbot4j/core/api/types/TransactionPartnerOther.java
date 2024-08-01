package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a transaction with an unknown source or recipient.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionPartnerOther implements TransactionPartner {
    /**
     * Type of the transaction partner, always “other”
     */
    @JsonProperty("type")
    private String type;
}
