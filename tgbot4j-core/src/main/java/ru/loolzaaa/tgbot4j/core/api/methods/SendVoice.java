package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.MultipartType;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMultipartMethod;
import ru.loolzaaa.tgbot4j.core.api.types.*;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;
import ru.loolzaaa.tgbot4j.core.pojo.MultipartBodyPart;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import static ru.loolzaaa.tgbot4j.core.api.MultipartType.Type.BINARY;
import static ru.loolzaaa.tgbot4j.core.api.MultipartType.Type.JSON;

/**
 * Use this method to send audio files,
 * if you want Telegram clients to display the file
 * as a playable voice message. For this to work,
 * your audio must be in an .OGG file encoded
 * with OPUS, or in .MP3 format, or in .M4A format
 * (other formats may be sent as {@link Audio} or {@link Document}).
 * On success, the sent {@link Message} is returned.
 * Bots can currently send voice messages of up to 50 MB in size,
 * this limit may be changed in the future.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendVoice implements TelegramMultipartMethod<Message> {
    /**
     * Unique identifier of the business connection
     * on behalf of which the message will be sent
     */
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

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
     * Audio file to send. Pass a file_id as String to send a voice
     * that exists on the Telegram servers (recommended),
     * pass an HTTP URL as a String for Telegram to get a file
     * from the Internet, or upload a new one using multipart/form-data.
     * <a href="https://core.telegram.org/bots/api#sending-files">More information on Sending Files »</a>
     */
    @Required
    @MultipartType(BINARY)
    @JsonProperty("voice")
    private InputFile voice;

    /**
     * Voice message caption,
     * 0-1024 characters after entities parsing
     */
    @JsonProperty("caption")
    private String caption;

    /**
     * Mode for parsing entities in the voice message caption.
     * See <a href="https://core.telegram.org/bots/api#formatting-options">formatting options</a> for more details.
     */
    @JsonProperty("parse_mode")
    private String parseMode;

    /**
     * A JSON-serialized list of special entities
     * that appear in the caption, which
     * can be specified instead of parse_mode
     */
    @MultipartType(JSON)
    @JsonProperty("caption_entities")
    private List<MessageEntity> captionEntities;

    /**
     * Duration of the voice message in seconds
     */
    @JsonProperty("duration")
    private Integer duration;

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
     * Pass True to allow up to 1000 messages per second,
     * ignoring <a href="https://core.telegram.org/bots/faq#how-can-i-message-all-of-my-bot-39s-subscribers-at-once">
     * broadcasting limits</a> for a fee of 0.1 Telegram Stars per message.
     * The relevant Stars will be withdrawn from the bot's balance
     */
    @JsonProperty("allow_paid_broadcast")
    private Boolean allowPaidBroadcast;

    /**
     * Unique identifier of the message effect to be added
     * to the message; for private chats only
     */
    @JsonProperty("message_effect_id")
    private String messageEffectId;

    /**
     * Description of the message to reply to
     */
    @MultipartType(JSON)
    @JsonProperty("reply_parameters")
    private ReplyParameters replyParameters;

    /**
     * Additional interface options.
     * A JSON-serialized object for an <a href="https://core.telegram.org/bots/features#inline-keyboards">inline keyboard</a>,
     * <a href="https://core.telegram.org/bots/features#keyboards">custom reply keyboard</a>, instructions to remove a reply keyboard
     * or to force a reply from the user.
     */
    @MultipartType(JSON)
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
        if (voice == null) {
            throw new ApiValidationException("Voice parameter can't be null", this);
        }
        voice.validate();
        if (replyMarkup != null) {
            replyMarkup.validate();
        }
    }

    @Override
    public void addBinaryBodyPart(List<MultipartBodyPart> parts, Field partField, String partName) throws IOException {
        if (partField.getName().equals("voice")) {
            addInputFileBodyPart(parts, voice, partName);
        }
    }
}
