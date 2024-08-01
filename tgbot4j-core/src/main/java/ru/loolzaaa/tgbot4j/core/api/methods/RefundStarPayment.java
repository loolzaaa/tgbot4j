package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Refunds a successful payment in <a href="https://t.me/BotNews/90">Telegram Stars</a>.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefundStarPayment implements TelegramMethod<Boolean> {
    /**
     * Identifier of the user whose payment will be refunded
     */
    @Required
    @JsonProperty("user_id")
    private Integer userId;

    /**
     * Telegram payment identifier
     */
    @Required
    @JsonProperty("telegram_payment_charge_id")
    private String telegramPaymentChargeId;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (userId == null) {
            throw new ApiValidationException("User ID parameter can't be null", this);
        }
        if (telegramPaymentChargeId == null) {
            throw new ApiValidationException("Telegram Payment Charge Id parameter can't be null", this);
        }
    }
}
