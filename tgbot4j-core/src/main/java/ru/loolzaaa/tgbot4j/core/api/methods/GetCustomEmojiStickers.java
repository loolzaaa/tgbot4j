package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.Sticker;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

import java.util.List;

/**
 * Use this method to get information about custom
 * emoji stickers by their identifiers.
 * Returns an Array of Sticker objects.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomEmojiStickers implements TelegramMethod<List<Sticker>> {
    /**
     * List of custom emoji identifiers.
     * At most 200 custom emoji identifiers can be specified.
     */
    @JsonProperty("custom_emoji_ids")
    private List<String> customEmojiIds;

    @Override
    public List<Sticker> determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeCollectionResponse(mapper, resultNode, Sticker.class);
    }

    @Override
    public void validate() {
        //TODO: need to check for empty?
        if (customEmojiIds == null || customEmojiIds.size() > 200) {
            throw new ApiValidationException("CustomEmojiIds parameter can't be null or size greater than 200", this);
        }
    }
}
