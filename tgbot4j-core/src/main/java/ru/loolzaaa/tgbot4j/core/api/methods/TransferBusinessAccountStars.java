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
 * Transfers Telegram Stars from the business account balance
 * to the bot's balance. Requires the can_transfer_stars business bot right.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferBusinessAccountStars implements TelegramMethod<Boolean> {
    /**
     * Unique identifier of the business connection
     */
    @Required
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    /**
     * Number of Telegram Stars to transfer; 1-10000
     */
    @Required
    @JsonProperty("star_count")
    private Integer starCount;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (businessConnectionId == null) {
            throw new ApiValidationException("Business Connection Id parameter can't be null", this);
        }
        if (starCount == null || starCount < 0 || starCount > 10000) {
            throw new ApiValidationException("Star Count parameter can't be null and must be in 0-10000", this);
        }
    }
}
