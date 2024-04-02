package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;

/**
 * Use this method to delete a sticker
 * from a set created by the bot.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteStickerFromSet implements TelegramMethod<Boolean> {
    /**
     * File identifier of the sticker
     */
    @Required
    @JsonProperty("sticker")
    private String sticker;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}
