package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.UserProfilePhotos;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to get a list of profile pictures for a user.
 * Returns a {@link UserProfilePhotos} object.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserProfilePhotos implements TelegramMethod<UserProfilePhotos> {
    /**
     * Unique identifier of the target user
     */
    @Required
    @JsonProperty("user_id")
    private Long userId;

    /**
     * Sequential number of the first photo to be returned.
     * By default, all photos are returned.
     */
    @JsonProperty("offset")
    private Integer offset;

    /**
     * Limits the number of photos to be retrieved.
     * Values between 1-100 are accepted.
     * Defaults to 100.
     */
    @JsonProperty("limit")
    private Integer limit;

    @Override
    public UserProfilePhotos determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, UserProfilePhotos.class);
    }

    @Override
    public void validate() {
        if (userId == null) {
            throw new ApiValidationException("User ID parameter can't be null", this);
        }
        if (limit != null && (limit < 1 || limit > 100)) {
            throw new ApiValidationException("Limit parameter must be in 1..100 range", this);
        }
    }
}
