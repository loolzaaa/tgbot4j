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
 * Allows the bot to cancel or re-enable extension
 * of a subscription paid in Telegram Stars.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditUserStarSubscription implements TelegramMethod<Boolean> {
    /**
     * Identifier of the user whose subscription will be edited
     */
    @Required
    @JsonProperty("user_id")
    private Integer userId;

    /**
     * Telegram payment identifier for the subscription
     */
    @Required
    @JsonProperty("telegram_payment_charge_id")
    private String telegramPaymentChargeId;

    /**
     * Pass True to cancel extension of the user subscription;
     * the subscription must be active up to the end
     * of the current subscription period.
     * Pass False to allow the user to re-enable a subscription
     * that was previously canceled by the bot.
     */
    @Required
    @JsonProperty("is_canceled")
    private Boolean isCanceled;

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
            throw new ApiValidationException("Telegram Payment Charge ID parameter can't be null", this);
        }
        if (isCanceled == null) {
            throw new ApiValidationException("Is Canceled parameter can't be null", this);
        }
    }
}
