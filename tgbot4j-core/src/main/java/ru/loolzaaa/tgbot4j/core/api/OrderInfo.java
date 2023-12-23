package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents information about an order.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo {
    /**
     * Optional. User name
     */
    @JsonProperty("name")
    private String name;

    /**
     * Optional. User's phone number
     */
    @JsonProperty("phone_number")
    private String phoneNumber;

    /**
     * Optional. User email
     */
    @JsonProperty("email")
    private String email;

    /**
     * Optional. User shipping address
     */
    @JsonProperty("shipping_address")
    private ShippingAddress shippingAddress;
}
