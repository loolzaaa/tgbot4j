package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a transaction with a user.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionPartnerUser implements TransactionPartner {
    /**
     * Type of the transaction partner, always “user”
     */
    @JsonProperty("type")
    private String type;

    /**
     * Information about the user
     */
    @JsonProperty("user")
    private User user;

    /**
     * Optional. Bot-specified invoice payload
     */
    @JsonProperty("invoice_payload")
    private String invoicePayload;
}
