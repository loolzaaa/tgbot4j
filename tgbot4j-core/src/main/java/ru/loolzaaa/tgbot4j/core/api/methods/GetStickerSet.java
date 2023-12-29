package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.StickerSet;

/**
 * Use this method to get a sticker set.
 * On success, a {@link StickerSet} object is returned.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetStickerSet implements TelegramMethod<StickerSet> {
    /**
     * Name of the sticker set
     */
    @JsonProperty("name")
    private String name;

    @Override
    public StickerSet determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, StickerSet.class);
    }
}
