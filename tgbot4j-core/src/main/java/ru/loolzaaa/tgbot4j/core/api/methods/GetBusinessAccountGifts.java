package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.OwnedGifts;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Returns the gifts received and owned by a managed business account.
 * Requires the can_view_gifts_and_stars business bot right.
 * Returns {@link OwnedGifts} on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBusinessAccountGifts implements TelegramMethod<OwnedGifts> {
    /**
     * Unique identifier of the business connection
     */
    @Required
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    /**
     * Pass True to exclude gifts that aren't saved
     * to the account's profile page
     */
    @JsonProperty("exclude_unsaved")
    private Boolean excludeUnsaved;

    /**
     * Pass True to exclude gifts that are saved
     * to the account's profile page
     */
    @JsonProperty("exclude_saved")
    private Boolean excludeSaved;

    /**
     * Pass True to exclude gifts that can be purchased
     * an unlimited number of times
     */
    @JsonProperty("exclude_unlimited")
    private Boolean excludeUnlimited;

    /**
     * Pass True to exclude gifts that can be purchased
     * a limited number of times
     */
    @JsonProperty("exclude_limited")
    private Boolean excludeLimited;

    /**
     * Pass True to exclude unique gifts
     */
    @JsonProperty("exclude_unique")
    private Boolean excludeUnique;

    /**
     * Pass True to sort results by gift price instead of send date.
     * Sorting is applied before pagination.
     */
    @JsonProperty("sort_by_price")
    private Boolean sortByPrice;

    /**
     * Offset of the first entry to return as received
     * from the previous request; use empty string
     * to get the first chunk of results
     */
    @JsonProperty("offset")
    private String offset;

    /**
     * The maximum number of gifts to be returned; 1-100.
     * Defaults to 100
     */
    @JsonProperty("limit")
    private Integer limit;

    @Override
    public OwnedGifts determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, OwnedGifts.class);
    }

    @Override
    public void validate() {
        if (businessConnectionId == null) {
            throw new ApiValidationException("Business Connection Id parameter can't be null", this);
        }
        if (limit != null && (limit < 1 || limit > 100)) {
            throw new ApiValidationException("Limit parameter must be in 1-100", this);
        }
    }
}
