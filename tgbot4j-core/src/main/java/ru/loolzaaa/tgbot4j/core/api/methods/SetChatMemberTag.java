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
 * Use this method to set a tag for a regular member
 * in a group or a supergroup. The bot must be an administrator
 * in the chat for this to work and must have
 * the can_manage_tags administrator right. Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetChatMemberTag implements TelegramMethod<Boolean> {
    /**
     * Unique identifier for the target chat or username
     * of the target supergroup (in the format {@code @supergroupusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Unique identifier of the target user
     */
    @Required
    @JsonProperty("user_id")
    private Integer userId;

    /**
     * New tag for the member; 0-16 characters, emoji are not allowed
     */
    @JsonProperty("tag")
    private String tag;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}
