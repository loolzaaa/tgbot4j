package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.ForumTopic;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to create a topic in a forum supergroup chat.
 * The bot must be an administrator in the chat for this to work
 * and must have the <i>can_manage_topics</i> administrator rights.
 * Returns information about the created topic as a ForumTopic object.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateForumTopic implements TelegramMethod<ForumTopic> {
    /**
     * Unique identifier for the target group or username
     * of the target supergroup or channel (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Topic name, 1-128 characters
     */
    @Required
    @JsonProperty("name")
    private String name;

    /**
     * Color of the topic icon in RGB format.
     * Currently, must be one of 7322096 (0x6FB9F0), 16766590 (0xFFD67E),
     * 13338331 (0xCB86DB), 9367192 (0x8EEE98), 16749490 (0xFF93B2),
     * or 16478047 (0xFB6F5F)
     */
    @JsonProperty("icon_color")
    private Integer iconColor;

    /**
     * Unique identifier of the custom emoji shown as the topic icon.
     * Use {@link GetForumTopicIconStickers} to get all allowed custom emoji identifiers.
     */
    @JsonProperty("icon_custom_emoji_id")
    private String iconCustomEmojiId;

    @Override
    public ForumTopic determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, ForumTopic.class);
    }

    @Override
    public void validate() {
        if (chatId == null) {
            throw new ApiValidationException("Chat ID parameter can't be null", this);
        }
        if (name == null) {
            throw new ApiValidationException("Name parameter can't be null", this);
        }
        if (name.isEmpty() || name.length() > 128) {
            throw new ApiValidationException("Name parameter can't be empty or greater than 128 characters", this);
        }
        if (!(iconColor == 7322096 || iconColor == 16766590 || iconColor == 13338331
                || iconColor == 9367192 || iconColor == 16749490 || iconColor == 16478047)) {
            throw new ApiValidationException("IconColor parameter is incorrect", this);
        }
    }
}
