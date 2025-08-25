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
 * Verifies a user <a href="https://telegram.org/verify#third-party-verification">on behalf of the organization</a>
 * which is represented by the bot. Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyUser implements TelegramMethod<Boolean> {
    /**
     * Unique identifier of the target user
     */
    @Required
    @JsonProperty("user_id")
    private Integer userId;

    /**
     * Custom description for the verification; 0-70 characters.
     * Must be empty if the organization isn't allowed
     * to provide a custom verification description.
     */
    @JsonProperty("custom_description")
    private String customDescription;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (userId == null) {
            throw new ApiValidationException("User ID parameter can't be null", this);
        }
    }
}
