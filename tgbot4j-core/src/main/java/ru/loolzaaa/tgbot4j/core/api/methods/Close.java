package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;

/**
 * Use this method to close the bot instance before moving it
 * from one local server to another. You need to delete the webhook
 * before calling this method to ensure that the bot isn't launched
 * again after server restart. The method will return error 429
 * in the first 10 minutes after the bot is launched.
 * Returns True on success. Requires no parameters.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Close implements TelegramMethod<Boolean> {
    /**
     * Dummy parameter for correct serialization,
     * because method requires no parameters
     */
    //TODO: remove it and others by global settings of mapper
    private Integer dummy;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}
