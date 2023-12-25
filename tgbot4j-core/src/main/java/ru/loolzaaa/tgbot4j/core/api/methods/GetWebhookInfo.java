package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.JsonResponseDeserializer;
import ru.loolzaaa.tgbot4j.core.api.types.WebhookInfo;

/**
 * Use this method to get current webhook status.
 * Requires no parameters.
 * On success, returns a {@link WebhookInfo} object.
 * If the bot is using {@link GetUpdates}, will return
 * an object with the url field empty.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetWebhookInfo implements JsonResponseDeserializer<WebhookInfo> {
    /**
     * Dummy parameter for correct serialization,
     * because method requires no parameters
     */
    private Integer dummy;

    @Override
    public WebhookInfo determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, WebhookInfo.class);
    }
}
