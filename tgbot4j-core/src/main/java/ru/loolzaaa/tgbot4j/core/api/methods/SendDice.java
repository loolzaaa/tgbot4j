package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.Message;
import ru.loolzaaa.tgbot4j.core.api.types.ReplyMarkup;
import ru.loolzaaa.tgbot4j.core.api.types.ReplyParameters;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to send an animated emoji
 * that will display a random value.
 * On success, the sent {@link Message} is returned.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendDice implements TelegramMethod<Message> {
    /**
     * Unique identifier for the target chat
     * or username of the target channel
     * (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Unique identifier for the target message
     * thread (topic) of the forum;
     * for forum supergroups only
     */
    @JsonProperty("message_thread_id")
    private Integer messageThreadId;

    /**
     * Emoji on which the dice throw animation is based.
     * Currently, must be one of “🎲”, “🎯”, “🏀”, “⚽”,
     * “🎳”, or “🎰”. Dice can have values 1-6
     * for “🎲”, “🎯” and “🎳”, values 1-5 for “🏀” and “⚽”,
     * and values 1-64 for “🎰”. Defaults to “🎲”
     */
    @JsonProperty("emoji")
    private String emoji;

    /**
     * Sends the message <a href="https://telegram.org/blog/channels-2-0#silent-messages">silently</a>.
     * Users will receive a notification with no sound.
     */
    @JsonProperty("disable_notification")
    private Boolean disableNotification;

    /**
     * Protects the contents of the sent message
     * from forwarding and saving
     */
    @JsonProperty("protect_content")
    private Boolean protectContent;

    /**
     * Description of the message to reply to
     */
    @JsonProperty("reply_parameters")
    private ReplyParameters replyParameters;

    /**
     * Additional interface options.
     * A JSON-serialized object for an <a href="https://core.telegram.org/bots/features#inline-keyboards">inline keyboard</a>,
     * <a href="https://core.telegram.org/bots/features#keyboards">custom reply keyboard</a>, instructions to remove reply keyboard
     * or to force a reply from the user.
     */
    @JsonProperty("reply_markup")
    private ReplyMarkup replyMarkup;

    @Override
    public Message determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Message.class);
    }

    @Override
    public void validate() {
        if (chatId == null || chatId.isEmpty()) {
            throw new ApiValidationException("Chat ID parameter can't be null or empty", this);
        }
        if (replyMarkup != null) {
            replyMarkup.validate();
        }
    }
}
