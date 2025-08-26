package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.InputProfilePhoto;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Changes the profile photo of a managed business account.
 * Requires the can_edit_profile_photo business bot right.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetBusinessAccountProfilePhoto implements TelegramMethod<Boolean> {
    /**
     * Unique identifier of the business connection
     */
    @Required
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    /**
     * The new profile photo to set
     */
    @Required
    @JsonProperty("photo")
    private InputProfilePhoto photo;

    /**
     * Pass True to set the public photo, which will be visible
     * even if the main photo is hidden
     * by the business account's privacy settings.
     * An account can have only one public photo.
     */
    @JsonProperty("is_public")
    private Boolean isPublic;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (businessConnectionId == null) {
            throw new ApiValidationException("Business Connection Id parameter can't be null", this);
        }
        if (photo == null) {
            throw new ApiValidationException("Photo parameter can't be null", this);
        }
    }
}
