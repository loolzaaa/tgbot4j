package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object contains information
 * about an incoming shipping query.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingQuery {
    /**
     * Unique query identifier
     */
    @JsonProperty("id")
    private String id;

    /**
     * User who sent the query
     */
    @JsonProperty("from")
    private User from;

    /**
     * Bot-specified invoice payload
     */
    @JsonProperty("invoice_payload")
    private String invoicePayload;

    /**
     * User specified shipping address
     */
    @JsonProperty("shipping_address")
    private ShippingAddress shippingAddress;
}
