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
 * Use this method to replace an existing sticker
 * in a sticker set with a new one.
 * The method is equivalent to calling {@link DeleteStickerFromSet},
 * then {@link AddStickerToSet}, then {@link SetStickerPositionInSet}.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplaceStickerInSet implements TelegramMethod<Boolean> {
    /**
     * User identifier of the sticker set owner
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
     * File identifier of the replaced sticker
     */
    @Required
    @JsonProperty("old_sticker")
    private String oldSticker;

    /**
     * A JSON-serialized object with information
     * about the added sticker. If exactly the same sticker
     * had already been added to the set,
     * then the set remains unchanged.
     */
    @Required
    @JsonProperty("sticker")
    private InputSticker sticker;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}
