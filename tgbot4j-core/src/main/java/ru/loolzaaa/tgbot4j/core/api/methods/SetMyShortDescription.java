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
 * Use this method to change the bot's short description,
 * which is shown on the bot's profile page and is sent together
 * with the link when users share the bot.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetMyShortDescription implements TelegramMethod<Boolean> {
    /**
     * New short description for the bot;
     * 0-120 characters.
     * Pass an empty string to remove the dedicated short description for the given language.
     */
    @JsonProperty("short_description")
    private String shortDescription;

    /**
     * A two-letter ISO 639-1 language code.
     * If empty, the short description will be applied
     * to all users for whose language there is no dedicated short description.
     */
    @JsonProperty("language_code")
    private String languageCode;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (shortDescription != null && shortDescription.length() > 120) {
            throw new ApiValidationException("ShortDescription parameter can't be greater than 120 characters", this);
        }
        if (languageCode != null && languageCode.length() > 2) {
            throw new ApiValidationException("LanguageCode parameter should be two-letter", this);
        }
    }
}
