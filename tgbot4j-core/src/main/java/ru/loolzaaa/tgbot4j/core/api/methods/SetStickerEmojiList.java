package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;

import java.util.List;

/**
 * Use this method to change the list of emoji assigned
 * to a regular or custom emoji sticker.
 * The sticker must belong to a sticker set
 * created by the bot. Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetStickerEmojiList implements TelegramMethod<Boolean> {
    /**
     * File identifier of the sticker
     */
    @Required
    @JsonProperty("sticker")
    private String sticker;

    /**
     * A JSON-serialized list of 1-20 emoji associated with the sticker
     */
    @Required
    @JsonProperty("emoji_list")
    private List<String> emojiList;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}
