package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a link to a video file stored on the Telegram servers.
 * By default, this video file will be sent by the user with an optional caption.
 * Alternatively, you can use input_message_content
 * to send a message with the specified content instead of the video.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InlineQueryResultCachedVideo {
    /**
     * Type of the result, must be video
     */
    @JsonProperty("type")
    private String type;

    /**
     * Unique identifier for this result, 1-64 bytes
     */
    @JsonProperty("id")
    private String id;

    /**
     * A valid file identifier for the video file
     */
    @JsonProperty("video_file_id")
    private String videoFileId;

    /**
     * Title for the result
     */
    @JsonProperty("title")
    private String title;

    /**
     * Optional. Short description of the result
     */
    @JsonProperty("description")
    private String description;

    /**
     * Optional. Caption of the video to be sent,
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
     * Optional. Content of the message to be sent instead of the video.
     * This field is <b>required</b> if InlineQueryResultVideo is used
     * to send an HTML-page as a result (e.g., a YouTube video).
     */
    @JsonProperty("input_message_content")
    private InputMessageContent inputMessageContent;
}
