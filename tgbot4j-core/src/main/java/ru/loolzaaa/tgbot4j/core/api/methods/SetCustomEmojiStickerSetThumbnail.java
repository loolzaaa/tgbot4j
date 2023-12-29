package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;

/**
 * Use this method to set the thumbnail of a custom
 * emoji sticker set. Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetCustomEmojiStickerSetThumbnail implements TelegramMethod<Boolean> {
    /**
     * Sticker set name
     */
    @JsonProperty("name")
    private String name;

    /**
     * Custom emoji identifier of a sticker from the sticker set;
     * pass an empty string to drop the thumbnail
     * and use the first sticker as the thumbnail.
     */
    @JsonProperty("custom_emoji_id")
    private String customEmojiId;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}
