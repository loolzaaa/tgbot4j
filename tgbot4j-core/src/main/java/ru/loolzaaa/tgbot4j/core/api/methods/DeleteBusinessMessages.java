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
 * Delete messages on behalf of a business account.
 * Requires the can_delete_sent_messages business bot right
 * to delete messages sent by the bot itself,
 * or the can_delete_all_messages business bot right
 * to delete any message. Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteBusinessMessages implements TelegramMethod<Boolean> {
    /**
     * Unique identifier of the business connection
     * on behalf of which to read the message
     */
    @Required
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    /**
     * A JSON-serialized list of 1-100 identifiers of messages to delete.
     * All messages must be from the same chat.
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
        if (businessConnectionId == null) {
            throw new ApiValidationException("Business Connection Id parameter can't be null", this);
        }
        if (messageIds == null) {
            throw new ApiValidationException("Message Ids parameter can't be null", this);
        }
    }
}
