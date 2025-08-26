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
 * Verifies a chat <a href="https://telegram.org/verify#third-party-verification">on behalf of the organization</a>
 * which is represented by the bot. Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyChat implements TelegramMethod<Boolean> {
    /**
     * Unique identifier for the target chat or username
     * of the target channel (in the format {@code @channelusername}).
     * Channel direct messages chats can't be verified.
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Custom description for the verification; 0-70 characters.
     * Must be empty if the organization isn't allowed
     * to provide a custom verification description.
     */
    @JsonProperty("custom_description")
    private String customDescription;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (chatId == null) {
            throw new ApiValidationException("Chat ID parameter can't be null", this);
        }
    }
}
