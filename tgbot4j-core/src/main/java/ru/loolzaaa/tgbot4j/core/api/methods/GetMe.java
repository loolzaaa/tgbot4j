package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.JsonResponseDeserializer;
import ru.loolzaaa.tgbot4j.core.api.types.User;

/**
 * A simple method for testing your bot's authentication token.
 * Requires no parameters. Returns basic information
 * about the bot in form of a {@link User} object.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMe implements JsonResponseDeserializer<User> {
    /**
     * Dummy parameter for correct serialization,
     * because method requires no parameters
     */
    //TODO: remove it and others by global settings of mapper
    private Integer dummy;

    @Override
    public User determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, User.class);
    }
}
