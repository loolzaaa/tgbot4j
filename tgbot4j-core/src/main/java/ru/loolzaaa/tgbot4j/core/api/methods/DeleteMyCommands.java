package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.BotCommandScope;
import ru.loolzaaa.tgbot4j.core.api.types.BotCommandScopeDefault;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to delete the list of the bot's commands
 * for the given scope and user language.
 * After deletion, <a href="https://core.telegram.org/bots/api#determining-list-of-commands">higher level commands</a> will be shown to affected users.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteMyCommands implements TelegramMethod<Boolean> {
    /**
     * A JSON-serialized object, describing scope of users
     * for which the commands are relevant.
     * Defaults to {@link BotCommandScopeDefault}.
     */
    @JsonProperty("scope")
    private BotCommandScope scope;

    /**
     * A two-letter ISO 639-1 language code.
     * If empty, commands will be applied to all users from the given scope,
     * for whose language there are no dedicated commands
     */
    @JsonProperty("language_code")
    private String languageCode;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (languageCode != null && languageCode.length() > 2) {
            throw new ApiValidationException("LanguageCode parameter should be two-letter", this);
        }
    }
}
