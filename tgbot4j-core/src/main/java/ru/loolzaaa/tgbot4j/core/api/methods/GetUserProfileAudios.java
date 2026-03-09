package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.UserProfileAudios;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to get a list of profile audios for a user.
 * Returns a {@link UserProfileAudios} object.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserProfileAudios implements TelegramMethod<UserProfileAudios> {
    /**
     * Unique identifier of the target user
     */
    @Required
    @JsonProperty("user_id")
    private Integer userId;

    /**
     * Sequential number of the first audio to be returned.
     * By default, all audios are returned.
     */
    @JsonProperty("offset")
    private Integer offset;

    /**
     * Limits the number of audios to be retrieved.
     * Values between 1-100 are accepted. Defaults to 100.
     */
    @JsonProperty("limit")
    private Integer limit;

    @Override
    public UserProfileAudios determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, UserProfileAudios.class);
    }

    @Override
    public void validate() {
        if (limit != null && (limit < 1 || limit > 100)) {
            throw new ApiValidationException("Limit parameter must be in range 1..100", this);
        }
    }
}
