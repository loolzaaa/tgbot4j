package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.BotName;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to get the current bot name
 * for the given user language.
 * Returns {@link BotName} on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMyName implements TelegramMethod<BotName> {
    /**
     * A two-letter ISO 639-1 language code or an empty string
     */
    @JsonProperty("language_code")
    private String languageCode;

    @Override
    public BotName determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, BotName.class);
    }

    @Override
    public void validate() {
        if (languageCode != null && languageCode.length() > 2) {
            throw new ApiValidationException("LanguageCode parameter should be two-letter", this);
        }
    }
}
