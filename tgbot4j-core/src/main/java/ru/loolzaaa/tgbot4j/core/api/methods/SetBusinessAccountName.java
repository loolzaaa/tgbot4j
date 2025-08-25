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
 * Changes the first and last name of a managed business account.
 * Requires the can_change_name business bot right.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetBusinessAccountName implements TelegramMethod<Boolean> {
    /**
     * Unique identifier of the business connection
     */
    @Required
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    /**
     * The new value of the first name for the business account;
     * 1-64 characters
     */
    @Required
    @JsonProperty("first_name")
    private String firstName;

    /**
     * The new value of the last name for the business account;
     * 0-64 characters
     */
    @JsonProperty("last_name")
    private String lastName;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (businessConnectionId == null) {
            throw new ApiValidationException("Business Connection Id parameter can't be null", this);
        }
        if (firstName == null || firstName.isEmpty() || firstName.length() > 64) {
            throw new ApiValidationException("First Name parameter can't be null, length must be 1-64", this);
        }
        if (lastName != null && lastName.length() > 64) {
            throw new ApiValidationException("Last Name parameter length must not exceed 64", this);
        }
    }
}
