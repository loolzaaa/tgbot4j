package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to set a new group sticker set for a supergroup.
 * The bot must be an administrator in the chat for this to work
 * and must have the appropriate administrator rights.
 * Use the field <i>can_set_sticker_set</i> optionally
 * returned in {@link GetChat} requests to check if the bot can use this method.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetChatStickerSet implements TelegramMethod<Boolean> {
    /**
     * Unique identifier for the target group or username
     * of the target supergroup or channel (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * 	Name of the sticker set to be set as the group sticker set
     */
    @Required
    @JsonProperty("sticker_set_name")
    private String stickerSetName;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (chatId == null) {
            throw new ApiValidationException("Chat ID parameter can't be null", this);
        }
        if (stickerSetName == null || stickerSetName.isEmpty()) {
            throw new ApiValidationException("User ID parameter can't be null or empty", this);
        }
    }
}
