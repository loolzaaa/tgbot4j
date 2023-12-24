package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a link to a video animation (H.264/MPEG-4 AVC video without sound).
 * By default, this animated MPEG-4 file will be sent by the user with optional caption.
 * Alternatively, you can use input_message_content to send a message
 * with the specified content instead of the animation.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InlineQueryResultMpeg4Gif {
    /**
     * Type of the result, must be mpeg4_gif
     */
    @JsonProperty("type")
    private String type;

    /**
     * Unique identifier for this result, 1-64 bytes
     */
    @JsonProperty("id")
    private String id;

    /**
     * A valid URL for the MPEG4 file. File size must not exceed 1MB
     */
    @JsonProperty("mpeg4_url")
    private String mpeg4Url;

    /**
     * Optional. Video width
     */
    @JsonProperty("mpeg4_width")
    private Integer mpeg4Width;

    /**
     * Optional. Video height
     */
    @JsonProperty("mpeg4_height")
    private Integer mpeg4Height;

    /**
     * Optional. Video duration in seconds
     */
    @JsonProperty("mpeg4_duration")
    private Integer mpeg4Duration;

    /**
     * URL of the static (JPEG or GIF) or animated (MPEG4) thumbnail for the result
     */
    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;

    /**
     * Optional. MIME type of the thumbnail, must be one of “image/jpeg”,
     * “image/gif”, or “video/mp4”. Defaults to “image/jpeg”
     */
    @JsonProperty("thumbnail_mime_type")
    private String thumbnailMimeType;

    /**
     * Optional. Title for the result
     */
    @JsonProperty("title")
    private String title;

    /**
     * Optional. Caption of the MPEG-4 file to be sent,
     * 0-1024 characters after entities parsing
     */
    @JsonProperty("caption")
    private String caption;

    /**
     * Optional. Mode for parsing entities in the photo caption.
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
     * Optional. <a href="https://core.telegram.org/bots/features#inline-keyboards">Inline keyboard</a> attached to the message
     */
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;

    /**
     * Optional. Content of the message to be sent
     * instead of the video animation
     */
    @JsonProperty("input_message_content")
    private InputMessageContent inputMessageContent;
}
