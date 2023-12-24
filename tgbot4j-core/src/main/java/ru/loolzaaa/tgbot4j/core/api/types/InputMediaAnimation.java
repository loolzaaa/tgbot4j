package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents an animation file (GIF or H.264/MPEG-4 AVC video
 * without sound) to be sent.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputMediaAnimation implements InputMedia {
    /**
     * Type of the result, must be animation
     */
    @JsonProperty("type")
    private String type;

    /**
     * File to send. Pass a file_id to send a file that exists
     * on the Telegram servers (recommended), pass an HTTP URL
     * for Telegram to get a file from the Internet,
     * or pass “attach://<file_attach_name>” to upload a new one
     * using multipart/form-data under <file_attach_name> name.
     * <a href="https://core.telegram.org/bots/api#sending-files">More information on Sending Files »</a>
     */
    @JsonProperty("media")
    private String media;

    /**
     * Optional. Thumbnail of the file sent; can be ignored
     * if thumbnail generation for the file is supported server-side.
     * The thumbnail should be in JPEG format and less than 200 kB in size.
     * A thumbnail's width and height should not exceed 320.
     * Ignored if the file is not uploaded using multipart/form-data.
     * Thumbnails can't be reused and can be only uploaded as a new file,
     * so you can pass “attach://<file_attach_name>” if the thumbnail
     * was uploaded using multipart/form-data under <file_attach_name>.
     * <a href="https://core.telegram.org/bots/api#sending-files">More information on Sending Files »</a>
     */
    @JsonProperty("thumbnail")
    private InputFile thumbnail;

    /**
     * Optional. Caption of the animation to be sent,
     * 0-1024 characters after entities parsing
     */
    @JsonProperty("caption")
    private String caption;

    /**
     * Optional. Mode for parsing entities in the animation caption.
     * See <a href="https://core.telegram.org/bots/api#formatting-options">formatting options</a> for more details.
     */
    @JsonProperty("parse_mode")
    private String parseMode;
    /**
     * Optional. List of special entities that appear in the caption,
     * which can be specified instead of parse_mode
     */
    @JsonProperty("caption_entities")
    private List<MessageEntity> captionEntities;

    /**
     * Optional. Animation width
     */
    @JsonProperty("width")
    private Integer width;

    /**
     * Optional. Animation height
     */
    @JsonProperty("height")
    private Integer height;

    /**
     * Optional. Animation duration in seconds
     */
    @JsonProperty("duration")
    private Integer duration;

    /**
     * Optional. Pass True if the animation needs to be covered
     * with a spoiler animation
     */
    @JsonProperty("has_spoiler")
    private Boolean hasSpoiler;
}
