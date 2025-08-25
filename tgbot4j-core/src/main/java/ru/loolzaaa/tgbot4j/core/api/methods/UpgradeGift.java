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

/**
 * Upgrades a given regular gift to a unique gift.
 * Requires the can_transfer_and_upgrade_gifts business bot right.
 * Additionally requires the can_transfer_stars business bot right
 * if the upgrade is paid. Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpgradeGift implements TelegramMethod<Boolean> {
    /**
     * Unique identifier of the business connection
     */
    @Required
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    /**
     * Unique identifier of the regular gift
     * that should be upgraded to a unique one
     */
    @Required
    @JsonProperty("owned_gift_id")
    private String ownedGiftId;

    /**
     * Pass True to keep the original gift text,
     * sender and receiver in the upgraded gift
     */
    @JsonProperty("keep_original_details")
    private Boolean keepOriginalDetails;

    /**
     * The amount of Telegram Stars that will be paid for the upgrade
     * from the business account balance. If {@code gift.prepaid_upgrade_star_count > 0},
     * then pass 0, otherwise, the can_transfer_stars business bot right
     * is required and {@code gift.upgrade_star_count} must be passed.
     */
    @JsonProperty("star_count")
    private Integer starCount;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (businessConnectionId == null) {
            throw new ApiValidationException("Business Connection Id parameter can't be null", this);
        }
        if (ownedGiftId == null) {
            throw new ApiValidationException("Owned Gift Id parameter can't be null", this);
        }
    }
}
