package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.JsonResponseDeserializer;
import ru.loolzaaa.tgbot4j.core.api.types.Update;

import java.util.List;

/**
 * Use this method to receive incoming updates
 * using long polling (<a href="https://en.wikipedia.org/wiki/Push_technology#Long_polling">wiki</a>).
 * Returns an Array of {@link Update} objects.
 *
 * @apiNote This method will not work if an outgoing webhook is set up.
 * @implNote In order to avoid getting duplicate updates,
 * recalculate offset after each server response.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUpdates implements JsonResponseDeserializer<List<Update>> {
    /**
     * Identifier of the first update to be returned.
     * Must be greater by one than the highest among the identifiers
     * of previously received updates. By default, updates
     * starting with the earliest unconfirmed update are returned.
     * An update is considered confirmed as soon as {@link GetUpdates}
     * is called with an offset higher than its update_id.
     * The negative offset can be specified to retrieve updates
     * starting from -offset update from the end of the updates queue.
     * All previous updates will be forgotten.
     */
    @JsonProperty("offset")
    private Integer offset;

    /**
     * Limits the number of updates to be retrieved.
     * Values between 1-100 are accepted. Defaults to 100.
     */
    @JsonProperty("limit")
    private Integer limit;

    /**
     * Timeout in seconds for long polling.
     * Defaults to 0, i.e. usual short polling.
     * Should be positive, short polling should be used
     * for testing purposes only.
     */
    @JsonProperty("timeout")
    private Integer timeout;

    /**
     * A JSON-serialized list of the update types
     * you want your bot to receive.
     * For example, specify {@code ["message", "edited_channel_post",
     * "callback_query"]} to only receive updates of these types.
     * See {@link Update} for a complete list of available update types.
     * Specify an empty list to receive all update types
     * except chat_member (default).
     * If not specified, the previous setting will be used.
     * <p>
     * Please note that this parameter doesn't affect updates
     * created before the call to the getUpdates,
     * so unwanted updates may be received
     * for a short period of time.
     */
    @JsonProperty("allowed_updates")
    private List<String> allowedUpdates;

    @Override
    public List<Update> determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeCollectionResponse(mapper, resultNode, Update.class);
    }
}
