package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.BotCommand;
import ru.loolzaaa.tgbot4j.core.api.types.BotCommandScope;
import ru.loolzaaa.tgbot4j.core.api.types.BotCommandScopeDefault;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

import java.util.List;

/**
 * Use this method to get the current list of the bot's commands
 * for the given scope and user language.
 * Returns an Array of {@link BotCommand} objects.
 * If commands aren't set, an empty list is returned.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMyCommands implements TelegramMethod<List<BotCommand>> {
    /**
     * A JSON-serialized object, describing scope of users
     * for which the commands are relevant.
     * Defaults to {@link BotCommandScopeDefault}.
     */
    @JsonProperty("scope")
    private BotCommandScope scope;

    /**
     * A two-letter ISO 639-1 language code or an empty string
     */
    @JsonProperty("language_code")
    private String languageCode;

    @Override
    public List<BotCommand> determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeCollectionResponse(mapper, resultNode, BotCommand.class);
    }

    @Override
    public void validate() {
        if (languageCode != null && languageCode.length() > 2) {
            throw new ApiValidationException("LanguageCode parameter should be two-letter", this);
        }
    }
}
