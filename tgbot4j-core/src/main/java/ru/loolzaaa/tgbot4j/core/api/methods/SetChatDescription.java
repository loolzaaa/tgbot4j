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
 * Use this method to change the description of a group, a supergroup or a channel.
 * The bot must be an administrator in the chat for this to work
 * and must have the appropriate administrator rights.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetChatDescription implements TelegramMethod<Boolean> {
    /**
     * Unique identifier for the target group or username
     * of the target supergroup or channel (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * New chat description, 0-255 characters
     */
    @JsonProperty("description")
    private String description;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (chatId == null) {
            throw new ApiValidationException("Chat ID parameter can't be null", this);
        }
        if (description != null && description.length() > 255) {
            throw new ApiValidationException("Description parameter can't be greater than 255 characters", this);
        }
    }
}
