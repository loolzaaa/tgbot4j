package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;

/**
 * Removes the profile photo of the bot.
 * Requires no parameters. Returns True on success.
 */

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class RemoveMyProfilePhoto implements TelegramMethod<Boolean> {
    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}
