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
 * Use this method to send photos.
 * On success, the sent {@link Message} is returned.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendPhoto implements TelegramMultipartMethod<Message> {
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
     * Photo to send. Pass a file_id as String to send a photo
     * that exists on the Telegram servers (recommended),
     * pass an HTTP URL as a String for Telegram to get a photo
     * from the Internet, or upload a new photo using multipart/form-data.
     * The photo must be at most 10 MB in size.
     * The photo's width and height must not exceed 10000 in total.
     * Width and height ratio must be at most 20.
     * <a href="https://core.telegram.org/bots/api#sending-files">More information on Sending Files »</a>
     */
    @Required
    @MultipartType(BINARY)
    @JsonProperty("photo")
    private InputFile photo;

    /**
     * Photo caption (may also be used when resending
     * photos by file_id), 0-1024 characters
     * after entities parsing
     */
    @JsonProperty("caption")
    private String caption;

    /**
     * Mode for parsing entities in the photo caption.
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
     * Pass True if the photo needs to be covered
     * with a spoiler animation
     */
    @JsonProperty("has_spoiler")
    private Boolean hasSpoiler;

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
    @MultipartType(JSON)
    @JsonProperty("reply_parameters")
    private ReplyParameters replyParameters;

    /**
     * Additional interface options.
     * A JSON-serialized object for an <a href="https://core.telegram.org/bots/features#inline-keyboards">inline keyboard</a>,
     * <a href="https://core.telegram.org/bots/features#keyboards">custom reply keyboard</a>, instructions to remove a reply keyboard
     * or to force a reply from the user.
     * Not supported for messages sent on behalf of a business account
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
        if (photo == null) {
            throw new ApiValidationException("Photo parameter can't be null", this);
        }
        photo.validate();
        if (replyMarkup != null) {
            replyMarkup.validate();
        }
    }

    @Override
    public void addBinaryBodyPart(List<MultipartBodyPart> parts, Field partField, String partName) throws IOException {
        if (partField.getName().equals("photo")) {
            addInputFileBodyPart(parts, photo, partName);
        }
    }
}
