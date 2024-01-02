package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a link to a file. By default, this file
 * will be sent by the user with an optional caption.
 * Alternatively, you can use input_message_content
 * to send a message with the specified content
 * instead of the file. Currently, only .PDF and .ZIP files
 * can be sent using this method.
 *
 * @apiNote This will only work in Telegram versions .
 * released after 9 April, 2016. Older clients will ignore them.
 */

@JsonDeserialize // Prevent stack overflow
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InlineQueryResultDocument implements InlineQueryResult {
    /**
     * Type of the result, must be document
     */
    @JsonProperty("type")
    private String type;

    /**
     * Unique identifier for this result, 1-64 bytes
     */
    @JsonProperty("id")
    private String id;

    /**
     * Title for the result
     */
    @JsonProperty("title")
    private String title;

    /**
     * Optional. Caption of the document to be sent,
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
     * A valid URL for the file
     */
    @JsonProperty("document_url")
    private String documentUrl;

    /**
     * MIME type of the content of the file,
     * either “application/pdf” or “application/zip”
     */
    @JsonProperty("mime_type")
    private String mimeType;

    /**
     * Optional. Short description of the result
     */
    @JsonProperty("description")
    private String description;

    /**
     * Optional. <a href="https://core.telegram.org/bots/features#inline-keyboards">Inline keyboard</a> attached to the message
     */
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;

    /**
     * Optional. Content of the message to be sent instead of the file
     */
    @JsonProperty("input_message_content")
    private InputMessageContent inputMessageContent;

    /**
     * Optional. URL of the thumbnail (JPEG only) for the file
     */
    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;

    /**
     * Optional. Thumbnail width
     */
    @JsonProperty("thumbnail_width")
    private Integer thumbnailWidth;

    /**
     * Optional. Thumbnail height
     */
    @JsonProperty("thumbnail_height")
    private Integer thumbnailHeight;
}
