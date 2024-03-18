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
 * Use this method to set the title of a created
 * sticker set. Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetStickerSetTitle implements TelegramMethod<Boolean> {
    /**
     * Sticker set name
     */
    @Required
    @JsonProperty("name")
    private String name;

    /**
     * Sticker set title, 1-64 characters
     */
    @Required
    @JsonProperty("title")
    private String title;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}
