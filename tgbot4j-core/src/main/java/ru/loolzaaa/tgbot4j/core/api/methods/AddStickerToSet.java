package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.InputSticker;

/**
 * Use this method to add a new sticker to a set
 * created by the bot. Emoji sticker sets can have
 * up to 200 stickers. Other sticker sets can have
 * up to 120 stickers. Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddStickerToSet implements TelegramMethod<Boolean> {
    /**
     * User identifier of sticker set owner
     */
    @Required
    @JsonProperty("user_id")
    private Long userId;

    /**
     * Sticker set name
     */
    @Required
    @JsonProperty("name")
    private String name;

    /**
     * A JSON-serialized object with information about the added sticker.
     * If exactly the same sticker had already been added to the set,
     * then the set isn't changed.
     */
    @Required
    @JsonProperty("sticker")
    private InputSticker sticker;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}
