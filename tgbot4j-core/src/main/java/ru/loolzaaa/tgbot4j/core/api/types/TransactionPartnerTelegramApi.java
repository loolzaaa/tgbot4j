package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a transaction with payment
 * for <a href="https://core.telegram.org/bots/api#paid-broadcasts">paid broadcasting</a>.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionPartnerTelegramApi {
    /**
     * Type of the transaction partner, always “telegram_api”
     */
    @JsonProperty("type")
    private String type;

    /**
     * The number of successful requests that exceeded
     * regular limits and were therefore billed
     */
    @JsonProperty("request_count")
    private Integer requestCount;
}
