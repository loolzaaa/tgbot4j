package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.Gifts;

/**
 * Returns the list of gifts that can be sent by the bot
 * to users and channel chats. Requires no parameters.
 * Returns a {@link Gifts} object.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAvailableGifts implements TelegramMethod<Gifts> {
    @Override
    public Gifts determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Gifts.class);
    }
}
