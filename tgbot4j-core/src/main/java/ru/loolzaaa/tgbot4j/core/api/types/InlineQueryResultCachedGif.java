package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a link to an animated GIF file stored on the Telegram servers.
 * By default, this animated GIF file will be sent by the user with an optional caption.
 * Alternatively, you can use input_message_content
 * to send a message with specified content instead of the animation.
 */

@JsonDeserialize // Prevent stack overflow
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InlineQueryResultCachedGif implements InlineQueryResult {
    /**
     * Type of the result, must be gif
     */
    @JsonProperty("type")
    private String type;

    /**
     * Unique identifier for this result, 1-64 bytes
     */
    @JsonProperty("id")
    private String id;

    /**
     * A valid file identifier for the GIF file
     */
    @JsonProperty("gif_file_id")
    private String gifFileId;

    /**
     * Optional. Title for the result
     */
    @JsonProperty("title")
    private String title;

    /**
     * Optional. Caption of the GIF file to be sent,
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
     * Optional. Pass True, if the caption must be shown above the message media
     */
    @JsonProperty("show_caption_above_media")
    private Boolean showCaptionAboveMedia;

    /**
     * Optional. <a href="https://core.telegram.org/bots/features#inline-keyboards">Inline keyboard</a> attached to the message
     */
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;

    /**
     * Optional. Content of the message to be sent instead of the GIF animation
     */
    @JsonProperty("input_message_content")
    private InputMessageContent inputMessageContent;
}
