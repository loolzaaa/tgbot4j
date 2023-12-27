package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;

/**
 * Use this method to remove webhook integration
 * if you decide to switch back to {@link GetUpdates}.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteWebhook implements TelegramMethod<Boolean> {
    /**
     * Pass True to drop all pending updates
     */
    @JsonProperty("drop_pending_updates")
    private Boolean dropPendingUpdates;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}
