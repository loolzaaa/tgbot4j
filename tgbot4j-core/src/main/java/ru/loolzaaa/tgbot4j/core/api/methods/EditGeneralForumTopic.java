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
 * Use this method to edit the name of the 'General' topic in a forum supergroup chat.
 * The bot must be an administrator in the chat for this to work
 * and must have the <i>can_manage_topics</i> administrator rights.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditGeneralForumTopic implements TelegramMethod<Boolean> {
    /**
     * Unique identifier for the target group or username
     * of the target supergroup or channel (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * New topic name, 1-128 characters
     */
    @Required
    @JsonProperty("name")
    private String name;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (chatId == null) {
            throw new ApiValidationException("Chat ID parameter can't be null", this);
        }
        if (name != null && name.length() > 128) {
            throw new ApiValidationException("Name parameter can't be empty or greater than 128 characters", this);
        }
    }
}
