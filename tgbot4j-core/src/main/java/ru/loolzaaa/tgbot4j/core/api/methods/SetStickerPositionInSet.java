package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;

/**
 * Use this method to move a sticker in a set
 * created by the bot to a specific position.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetStickerPositionInSet implements TelegramMethod<Boolean> {
    /**
     * File identifier of the sticker
     */
    @JsonProperty("sticker")
    private String sticker;

    /**
     * New sticker position in the set, zero-based
     */
    @JsonProperty("position")
    private Integer position;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}
