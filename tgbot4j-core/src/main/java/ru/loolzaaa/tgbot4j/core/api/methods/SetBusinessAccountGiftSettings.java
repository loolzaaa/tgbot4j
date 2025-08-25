package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.AcceptedGiftTypes;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Changes the privacy settings pertaining to incoming gifts
 * in a managed business account.
 * Requires the can_change_gift_settings business bot right.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetBusinessAccountGiftSettings implements TelegramMethod<Boolean> {
    /**
     * Unique identifier of the business connection
     */
    @Required
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    /**
     * Pass True, if a button for sending a gift to the user
     * or by the business account must always be shown
     * in the input field
     */
    @Required
    @JsonProperty("show_gift_button")
    private Boolean showGiftButton;

    /**
     * Types of gifts accepted by the business account
     */
    @Required
    @JsonProperty("accepted_gift_types")
    private AcceptedGiftTypes acceptedGiftTypes;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (businessConnectionId == null) {
            throw new ApiValidationException("Business Connection Id parameter can't be null", this);
        }
        if (showGiftButton == null) {
            throw new ApiValidationException("Show Gift Button parameter can't be null", this);
        }
        if (acceptedGiftTypes == null) {
            throw new ApiValidationException("Accepted Gift Types parameter can't be null", this);
        }
    }
}
