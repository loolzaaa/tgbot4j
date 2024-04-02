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

import java.util.List;

/**
 * Use this method to delete multiple messages simultaneously.
 * If some of the specified messages can't be found,
 * they are skipped. Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteMessages implements TelegramMethod<Boolean> {
    /**
     * Unique identifier for the target chat or
     * username of the target channel
     * (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Identifiers of 1-100 messages to delete.
     * See {@link DeleteMessage} for limitations
     * on which messages can be deleted
     */
    @Required
    @JsonProperty("message_ids")
    private List<Integer> messageIds;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (chatId == null) {
            throw new ApiValidationException("Chat ID parameter can't be null", this);
        }
        if (messageIds == null || messageIds.isEmpty()) {
            throw new ApiValidationException("Message IDs parameter can't be null or empty", this);
        }
        if (messageIds.size() > 100) {
            throw new ApiValidationException("Message IDs parameter must be in 1..100 range", this);
        }
    }
}
