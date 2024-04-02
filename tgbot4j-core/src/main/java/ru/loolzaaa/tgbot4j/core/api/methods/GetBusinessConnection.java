package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.BusinessConnection;

/**
 * Use this method to get information about the connection
 * of the bot with a business account.
 * Returns a {@link BusinessConnection} object on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBusinessConnection implements TelegramMethod<BusinessConnection> {
    /**
     * Unique identifier of the business connection
     */
    @Required
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    @Override
    public BusinessConnection determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, BusinessConnection.class);
    }
}
