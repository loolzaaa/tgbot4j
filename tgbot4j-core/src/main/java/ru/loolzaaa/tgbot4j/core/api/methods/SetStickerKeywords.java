package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;

import java.util.List;

/**
 * Use this method to change search keywords assigned
 * to a regular or custom emoji sticker.
 * The sticker must belong to a sticker set
 * created by the bot. Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetStickerKeywords implements TelegramMethod<Boolean> {
    /**
     * File identifier of the sticker
     */
    @JsonProperty("sticker")
    private String sticker;
    /**
     * A JSON-serialized list of 0-20 search keywords
     * for the sticker with total length of up to 64 characters
     */
    @JsonProperty("keywords")
    private List<String> keywords;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}
