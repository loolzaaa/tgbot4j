package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.MessageId;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

import java.util.List;

/**
 * Use this method to forward multiple messages of any kind.
 * If some of the specified messages can't be found or forwarded,
 * they are skipped. Service messages and messages
 * with protected content can't be forwarded.
 * Album grouping is kept for forwarded messages.
 * On success, an array of {@link MessageId} of the sent messages
 * is returned.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForwardMessages implements TelegramMethod<List<MessageId>> {
    /**
     * Unique identifier for the target chat or username
     * of the target channel (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Unique identifier for the target message thread (topic)
     * of the forum; for forum supergroups only
     */
    @JsonProperty("message_thread_id")
    private Integer messageThreadId;

    /**
     * Unique identifier for the chat where the original
     * messages were sent (or channel username
     * in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("from_chat_id")
    private String fromChatId;

    /**
     * Identifiers of 1-100 messages in the chat from_chat_id
     * to forward. The identifiers must be specified
     * in a strictly increasing order.
     */
    @Required
    @JsonProperty("message_ids")
    private List<Integer> messageIds;

    /**
     * Sends the messages <a href="https://telegram.org/blog/channels-2-0#silent-messages">silently</a>.
     * Users will receive a notification with no sound.
     */
    @JsonProperty("disable_notification")
    private Boolean disableNotification;

    /**
     * Protects the contents of the forwarded messages
     * from forwarding and saving
     */
    @JsonProperty("protect_content")
    private Boolean protectContent;

    @Override
    public List<MessageId> determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeCollectionResponse(mapper, resultNode, MessageId.class);
    }

    @Override
    public void validate() {
        if (chatId == null) {
            throw new ApiValidationException("Chat ID parameter can't be null", this);
        }
        if (fromChatId == null) {
            throw new ApiValidationException("From Chat ID parameter can't be null", this);
        }
        if (messageIds == null) {
            throw new ApiValidationException("Message IDs parameter can't be null", this);
        }
        if (messageIds.isEmpty() || messageIds.size() > 100) {
            throw new ApiValidationException("Message IDs parameter must be in 1..100 range", this);
        }
    }
}
