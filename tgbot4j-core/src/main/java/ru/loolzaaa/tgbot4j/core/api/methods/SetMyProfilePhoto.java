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

/**
 * Changes the profile photo of the bot. Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetMyProfilePhoto implements TelegramMethod<Boolean> {
    /**
     * The new profile photo to set
     */
    @Required
    @JsonProperty("photo")
    private InputProfilePhoto photo;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}
