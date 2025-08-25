package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.StarAmount;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Returns the amount of Telegram Stars owned
 * by a managed business account.
 * Requires the can_view_gifts_and_stars business bot right.
 * Returns {@link StarAmount} on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBusinessAccountStarBalance implements TelegramMethod<StarAmount> {
    /**
     * Unique identifier of the business connection
     * on behalf of which to read the message
     */
    @Required
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    @Override
    public StarAmount determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, StarAmount.class);
    }

    @Override
    public void validate() {
        if (businessConnectionId == null) {
            throw new ApiValidationException("Business Connection Id parameter can't be null", this);
        }
    }
}
