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
 * Use this method to delete a message,
 * including service messages,
 * with the following limitations:
 * <ul>
 *     <li>A message can only be deleted if it was sent less than 48 hours ago.</li>
 *     <li>Service messages about a supergroup, channel, or forum topic creation can't be deleted.</li>
 *     <li>A dice message in a private chat can only be deleted if it was sent more than 24 hours ago.</li>
 *     <li>Bots can delete outgoing messages in private chats, groups, and supergroups.</li>
 *     <li>Bots can delete incoming messages in private chats.</li>
 *     <li>Bots granted can_post_messages permissions can delete outgoing messages in channels.</li>
 *     <li>If the bot is an administrator of a group, it can delete any message there.</li>
 *     <li>If the bot has can_delete_messages administrator right in a supergroup or a channel, it can delete any message there.</li>
 *     <li>If the bot has can_manage_direct_messages administrator right in a channel, it can delete any message in the corresponding direct messages chat.</li>
 * </ul>
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteMessage implements TelegramMethod<Boolean> {
    /**
     * Unique identifier for the target chat or username
     * of the target channel (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Identifier of the message to delete
     */
    @Required
    @JsonProperty("message_id")
    private Integer messageId;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}
