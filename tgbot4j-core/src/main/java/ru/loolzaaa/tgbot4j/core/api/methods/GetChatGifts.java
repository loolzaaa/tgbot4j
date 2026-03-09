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
 * Returns the gifts owned by a chat.
 * Returns {@link OwnedGifts} on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetChatGifts implements TelegramMethod<OwnedGifts> {
    /**
     * Unique identifier for the target chat or username
     * of the target channel (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Pass True to exclude gifts that aren't saved to the chat's profile page.
     * Always True, unless the bot has the can_post_messages
     * administrator right in the channel.
     */
    @JsonProperty("exclude_unsaved")
    private Boolean excludeUnsaved;

    /**
     * Pass True to exclude gifts that are saved to the chat's profile page.
     * Always False, unless the bot has the can_post_messages
     * administrator right in the channel.
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
     * a limited number of times and can be upgraded to unique
     */
    @JsonProperty("exclude_limited_upgradable")
    private Boolean excludeLimitedUpgradable;

    /**
     * Pass True to exclude gifts that can be purchased
     * a limited number of times and can't be upgraded to unique
     */
    @JsonProperty("exclude_limited_non_upgradable")
    private Boolean excludeLimitedNonUpgradable;

    /**
     * Pass True to exclude gifts that were assigned
     * from the TON blockchain and can't be resold
     * or transferred in Telegram
     */
    @JsonProperty("exclude_from_blockchain")
    private Boolean excludeFromBlockchain;

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
     * from the previous request; use an empty string
     * to get the first chunk of results
     */
    @JsonProperty("offset")
    private String offset;

    /**
     * The maximum number of gifts to be returned;
     * 1-100. Defaults to 100
     */
    @JsonProperty("limit")
    private Integer limit;

    @Override
    public OwnedGifts determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, OwnedGifts.class);
    }

    @Override
    public void validate() {
        if (chatId == null || chatId.isEmpty()) {
            throw new ApiValidationException("Chat ID parameter can't be null or empty", this);
        }
        if (limit != null && (limit < 1 || limit > 100)) {
            throw new ApiValidationException("Limit parameter must be in range 1-100", this);
        }
    }
}
