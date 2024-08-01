package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a withdrawal transaction to the Telegram Ads platform.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionPartnerTelegramAds implements TransactionPartner {
    /**
     * Type of the transaction partner, always “telegram_ads”
     */
    @JsonProperty("type")
    private String type;
}
