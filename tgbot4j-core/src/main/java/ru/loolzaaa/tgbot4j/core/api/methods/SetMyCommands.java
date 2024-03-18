package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.BotCommand;
import ru.loolzaaa.tgbot4j.core.api.types.BotCommandScope;
import ru.loolzaaa.tgbot4j.core.api.types.BotCommandScopeDefault;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

import java.util.List;

/**
 * Use this method to change the list of the bot's commands.
 * See <a href="https://core.telegram.org/bots/features#commands">this manual</a> for more details about bot commands.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetMyCommands implements TelegramMethod<Boolean> {
    /**
     * A JSON-serialized list of bot commands to be set as the list of the bot's commands.
     * At most 100 commands can be specified.
     */
    @Required
    @JsonProperty("commands")
    private List<BotCommand> commands;

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
        if (commands == null) {
            throw new ApiValidationException("Commands parameter can't be null", this);
        }
        if (commands.size() > 100) {
            throw new ApiValidationException("Commands parameter can't be greater than 100 commands", this);
        }
        if (languageCode != null && languageCode.length() > 2) {
            throw new ApiValidationException("LanguageCode parameter should be two-letter", this);
        }
    }
}
