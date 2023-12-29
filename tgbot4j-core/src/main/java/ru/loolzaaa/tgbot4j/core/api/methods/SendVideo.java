package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.MultipartType;
import ru.loolzaaa.tgbot4j.core.api.TelegramMultipartMethod;
import ru.loolzaaa.tgbot4j.core.api.types.*;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;
import ru.loolzaaa.tgbot4j.core.pojo.MultipartBodyPart;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import static ru.loolzaaa.tgbot4j.core.api.MultipartType.Type.*;

/**
 * Use this method to send video files,
 * Telegram clients support MPEG4 videos
 * (other formats may be sent as {@link Document}).
 * On success, the sent {@link Message} is returned.
 * Bots can currently send video files of up to 50 MB in size,
 * this limit may be changed in the future.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendVideo implements TelegramMultipartMethod<Message> {
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
     * Video to send. Pass a file_id as String to send a video
     * that exists on the Telegram servers (recommended),
     * pass an HTTP URL as a String for Telegram to get a video
     * from the Internet, or upload a new video using multipart/form-data.
     * <a href="https://core.telegram.org/bots/api#sending-files">More information on Sending Files »</a>
     */
    @MultipartType(BINARY)
    @JsonProperty("video")
    private InputFile video;

    /**
     * Duration of sent video in seconds
     */
    @JsonProperty("duration")
    private Integer duration;

    /**
     * Video width
     */
    @JsonProperty("width")
    private Integer width;

    /**
     * Video height
     */
    @JsonProperty("height")
    private Integer height;

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
     * Video caption (may also be used when resending
     * videos by file_id), 0-1024 characters after entities parsing
     */
    @JsonProperty("caption")
    private String caption;

    /**
     * Mode for parsing entities in the video caption.
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
     * Pass True if the video needs to be covered
     * with a spoiler animation
     */
    @JsonProperty("has_spoiler")
    private Boolean hasSpoiler;

    /**
     * Pass True if the uploaded video is suitable for streaming
     */
    @JsonProperty("supports_streaming")
    private Boolean supportsStreaming;

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
        if (video == null) {
            throw new ApiValidationException("Video parameter can't be null", this);
        }
        video.validate();
        if (thumbnail != null && thumbnail.getFile() == null && thumbnail.getInputStream() == null) {
            throw new ApiValidationException("Thumbnail parameter must be uploaded with multipart/form-data", this);
        }
        //TODO: replyMarkup.validate() <--- may be null
    }

    @Override
    public void addBinaryBodyPart(List<MultipartBodyPart> parts, Field partField, String partName) throws IOException {
        if (partField.getName().equals("video")) {
            parts.add(new MultipartBodyPart(partName, video.getAttachName().getBytes(StandardCharsets.UTF_8), false));
            if (video.getFile() != null) {
                parts.add(new MultipartBodyPart(video.getInputName(), Files.readAllBytes(video.getFile().toPath()), true));
                return;
            }
            if (video.getInputStream() != null) {
                parts.add(new MultipartBodyPart(video.getInputName(), video.getInputStream().readAllBytes(), true));
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
