package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.Sticker;

/**
 * Use this method to get custom emoji stickers,
 * which can be used as a forum topic icon by any user.
 * Requires no parameters.
 * Returns an Array of {@link Sticker} objects.
 */

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class GetForumTopicIconStickers implements TelegramMethod<Sticker> {
    @Override
    public Sticker determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Sticker.class);
    }
}
