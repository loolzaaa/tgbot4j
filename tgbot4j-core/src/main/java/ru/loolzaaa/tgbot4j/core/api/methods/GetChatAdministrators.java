package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.ChatMember;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

import java.util.List;

/**
 * Use this method to get a list of administrators in a chat, which aren't bots.
 * Returns an Array of ChatMember objects.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetChatAdministrators implements TelegramMethod<List<ChatMember>> {
    /**
     * Unique identifier for the target group or username
     * of the target supergroup or channel (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    @Override
    public List<ChatMember> determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeCollectionResponse(mapper, resultNode, ChatMember.class);
    }

    @Override
    public void validate() {
        if (chatId == null) {
            throw new ApiValidationException("Chat ID parameter can't be null", this);
        }
    }
}
