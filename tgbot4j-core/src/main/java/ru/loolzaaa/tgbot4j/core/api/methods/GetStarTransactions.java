package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.StarTransactions;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Returns the bot's Telegram Star transactions in chronological order.
 * On success, returns a {@link StarTransactions} object.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetStarTransactions implements TelegramMethod<StarTransactions> {
    /**
     * Number of transactions to skip in the response
     */
    @JsonProperty("offset")
    private Integer offset;

    /**
     * The maximum number of transactions to be retrieved.
     * Values between 1-100 are accepted. Defaults to 100.
     */
    @JsonProperty("limit")
    private Integer limit;

    @Override
    public StarTransactions determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, StarTransactions.class);
    }

    @Override
    public void validate() {
        if (limit != null && (limit < 1 || limit > 100)) {
            throw new ApiValidationException("Limit parameter must be in 1..100 is specified", this);
        }
    }
}
