package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.MaskPosition;

/**
 * Use this method to change the {@link MaskPosition} of a mask sticker.
 * The sticker must belong to a sticker set that was created by the bot.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetStickerMaskPosition implements TelegramMethod<Boolean> {
    /**
     * File identifier of the sticker
     */
    @JsonProperty("sticker")
    private String sticker;

    /**
     * A JSON-serialized object with the position
     * where the mask should be placed on faces.
     * Omit the parameter to remove the mask position.
     */
    @JsonProperty("mask_position")
    private MaskPosition maskPosition;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}
