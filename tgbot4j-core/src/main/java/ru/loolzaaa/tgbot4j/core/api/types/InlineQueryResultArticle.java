package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a link to an article or web page.
 */

@JsonDeserialize // Prevent stack overflow
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InlineQueryResultArticle implements InlineQueryResult {
    /**
     * Type of the result, must be article
     */
    @JsonProperty("type")
    private String type;

    /**
     * Unique identifier for this result, 1-64 Bytes
     */
    @JsonProperty("id")
    private String id;

    /**
     * Title of the result
     */
    @JsonProperty("title")
    private String title;

    /**
     * Content of the message to be sent
     */
    @JsonProperty("input_message_content")
    private InputMessageContent inputMessageContent;

    /**
     * Optional. Inline keyboard attached to the message
     */
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;

    /**
     * Optional. URL of the result
     */
    @JsonProperty("url")
    private String url;

    /**
     * Optional. Short description of the result
     */
    @JsonProperty("description")
    private String description;

    /**
     * Optional. Url of the thumbnail for the result
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
