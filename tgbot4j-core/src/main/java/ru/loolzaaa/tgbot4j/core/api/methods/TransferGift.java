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
 * Transfers an owned unique gift to another user.
 * Requires the can_transfer_and_upgrade_gifts business bot right.
 * Requires can_transfer_stars business bot right
 * if the transfer is paid. Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferGift implements TelegramMethod<Boolean> {
    /**
     * Unique identifier of the business connection
     */
    @Required
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    /**
     * Unique identifier of the regular gift
     * that should be transferred
     */
    @Required
    @JsonProperty("owned_gift_id")
    private String ownedGiftId;

    /**
     * Unique identifier of the chat which will own the gift.
     * The chat must be active in the last 24 hours.
     */
    @Required
    @JsonProperty("new_owner_chat_id")
    private Integer newOwnerChatId;

    /**
     * The amount of Telegram Stars that will be paid
     * for the transfer from the business account balance.
     * If positive, then the can_transfer_stars business bot right
     * is required.
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
