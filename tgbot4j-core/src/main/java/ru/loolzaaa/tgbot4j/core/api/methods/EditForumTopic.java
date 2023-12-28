package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to edit name and icon of a topic in a forum supergroup chat.
 * The bot must be an administrator in the chat for this to work
 * and must have <i>can_manage_topics</i> administrator rights, unless it is the creator of the topic.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditForumTopic implements TelegramMethod<Boolean> {
    /**
     * Unique identifier for the target group or username
     * of the target supergroup or channel (in the format {@code @channelusername})
     */
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Unique identifier for the target message thread of the forum topic
     */
    @JsonProperty("message_thread_id")
    private Integer messageThreadId;

    /**
     * New topic name, 0-128 characters.
     * If not specified or empty,
     * the current name of the topic will be kept
     */
    @JsonProperty("name")
    private String name;

    /**
     * New unique identifier of the custom emoji shown as the topic icon.
     * Use {@link GetForumTopicIconStickers} to get all allowed custom emoji identifiers.
     * Pass an empty string to remove the icon.
     * If not specified, the current icon will be kept
     */
    @JsonProperty("icon_custom_emoji_id")
    private String iconCustomEmojiId;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (chatId == null) {
            throw new ApiValidationException("Chat ID parameter can't be null", this);
        }
        if (messageThreadId == null) {
            throw new ApiValidationException("MessageThreadId parameter can't be null", this);
        }
        if (name != null && name.length() > 128) {
            throw new ApiValidationException("Name parameter can't be empty or greater than 128 characters", this);
        }
    }
}
