package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.MultipartType;
import ru.loolzaaa.tgbot4j.core.api.TelegramMultipartMethod;
import ru.loolzaaa.tgbot4j.core.api.types.InputFile;
import ru.loolzaaa.tgbot4j.core.api.types.Message;
import ru.loolzaaa.tgbot4j.core.api.types.MessageEntity;
import ru.loolzaaa.tgbot4j.core.api.types.ReplyMarkup;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;
import ru.loolzaaa.tgbot4j.core.pojo.MultipartBodyPart;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import static ru.loolzaaa.tgbot4j.core.api.MultipartType.Type.*;

/**
 * Use this method to send audio files,
 * if you want Telegram clients to display them in the music player.
 * Your audio must be in the .MP3 or .M4A format.
 * On success, the sent {@link Message} is returned.
 * Bots can currently send audio files of up to 50 MB in size,
 * this limit may be changed in the future.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendAudio implements TelegramMultipartMethod<Message> {
    /**
     * Unique identifier for the target chat
     * or username of the target channel
     * (in the format {@code @channelusername})
     */
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
     * Audio file to send. Pass a file_id as String to send a audio
     * that exists on the Telegram servers (recommended),
     * pass an HTTP URL as a String for Telegram to get a audio file
     * from the Internet, or upload a new one using multipart/form-data.
     * <a href="https://core.telegram.org/bots/api#sending-files">More information on Sending Files »</a>
     */
    @MultipartType(BINARY)
    @JsonProperty("audio")
    private InputFile audio;

    /**
     * Audio caption, 0-1024 characters after entities parsing
     */
    @JsonProperty("caption")
    private String caption;

    /**
     * Mode for parsing entities in the audio caption.
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
     * Duration of the audio in seconds
     */
    @JsonProperty("duration")
    private Integer duration;

    /**
     * Performer
     */
    @JsonProperty("performer")
    private String performer;

    /**
     * Track name
     */
    @JsonProperty("title")
    private String title;

    /**
     * Thumbnail of the file sent; can be ignored
     * if thumbnail generation for the file
     * is supported server-side. The thumbnail
     * should be in JPEG format and less than 200 kB in size.
     * A thumbnail's width and height should not exceed 320.
     * Ignored if the file is not uploaded using multipart/form-data.
     * Thumbnails can't be reused and can be only uploaded
     * as a new file, so you can pass “attach://<file_attach_name>”
     * if the thumbnail was uploaded using multipart/form-data
     * under <file_attach_name>.
     * <a href="https://core.telegram.org/bots/api#sending-files">More information on Sending Files »</a>
     */
    @MultipartType(BINARY)
    @JsonProperty("thumbnail")
    private InputFile thumbnail;

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
     * If the message is a reply, ID of the original message
     */
    @JsonProperty("reply_to_message_id")
    private Integer replyToMessageId;

    /**
     * Pass True if the message should be sent
     * even if the specified replied-to message
     * is not found
     */
    @JsonProperty("allow_sending_without_reply")
    private Boolean allowSendingWithoutReply;

    /**
     * Additional interface options.
     * A JSON-serialized object for an <a href="https://core.telegram.org/bots/features#inline-keyboards">inline keyboard</a>,
     * <a href="https://core.telegram.org/bots/features#keyboards">custom reply keyboard</a>, instructions to remove reply keyboard
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
        if (audio == null) {
            throw new ApiValidationException("Audio parameter can't be null", this);
        }
        audio.validate();
        if (thumbnail != null && thumbnail.getFile() == null && thumbnail.getInputStream() == null) {
            throw new ApiValidationException("Thumbnail parameter must be uploaded with multipart/form-data", this);
        }
        if (replyMarkup != null) {
            replyMarkup.validate();
        }
    }

    @Override
    public void addBinaryBodyPart(List<MultipartBodyPart> parts, Field partField, String partName) throws IOException {
        if (partField.getName().equals("audio")) {
            parts.add(new MultipartBodyPart(partName, audio.getAttachName().getBytes(StandardCharsets.UTF_8), false));
            if (audio.getFile() != null) {
                parts.add(new MultipartBodyPart(audio.getInputName(), Files.readAllBytes(audio.getFile().toPath()), true));
                return;
            }
            if (audio.getInputStream() != null) {
                parts.add(new MultipartBodyPart(audio.getInputName(), audio.getInputStream().readAllBytes(), true));
            }
        }
        if (partField.getName().equals("thumbnail")) {
            parts.add(new MultipartBodyPart(partName, thumbnail.getAttachName().getBytes(StandardCharsets.UTF_8), false));
            if (thumbnail.getFile() != null) {
                parts.add(new MultipartBodyPart(thumbnail.getInputName(), Files.readAllBytes(thumbnail.getFile().toPath()), true));
                return;
            }
            if (thumbnail.getInputStream() != null) {
                parts.add(new MultipartBodyPart(thumbnail.getInputName(), thumbnail.getInputStream().readAllBytes(), true));
            }
        }
    }
}
