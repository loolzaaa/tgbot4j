package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.StarAmount;

/**
 * A method to get the current Telegram Stars balance
 * of the bot. Requires no parameters.
 * On success, returns a {@link StarAmount} object.
 */

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class GetMyStarBalance implements TelegramMethod<StarAmount> {
    @Override
    public StarAmount determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, StarAmount.class);
    }
}
