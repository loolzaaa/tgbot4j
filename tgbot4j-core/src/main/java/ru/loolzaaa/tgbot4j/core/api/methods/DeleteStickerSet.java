package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;

/**
 * Use this method to delete a sticker set
 * that was created by the bot. Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteStickerSet implements TelegramMethod<Boolean> {
    /**
     * Sticker set name
     */
    @JsonProperty("name")
    private String name;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}
