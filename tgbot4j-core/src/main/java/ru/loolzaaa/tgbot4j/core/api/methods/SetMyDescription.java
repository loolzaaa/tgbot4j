package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to change the bot's description,
 * which is shown in the chat with the bot if the chat is empty.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetMyDescription implements TelegramMethod<Boolean> {
    /**
     * New bot description;
     * 0-512 characters.
     * Pass an empty string to remove
     * the dedicated description for the given language.
     */
    @JsonProperty("description")
    private String description;

    /**
     * A two-letter ISO 639-1 language code.
     * If empty, the description will be applied to all users
     * for whose language there is no dedicated description
     */
    @JsonProperty("language_code")
    private String languageCode;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (description != null && description.length() > 512) {
            throw new ApiValidationException("Description parameter can't be greater than 512 characters", this);
        }
        if (languageCode != null && languageCode.length() > 2) {
            throw new ApiValidationException("LanguageCode parameter should be two-letter", this);
        }
    }
}
