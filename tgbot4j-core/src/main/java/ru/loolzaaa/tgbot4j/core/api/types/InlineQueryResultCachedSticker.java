package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a link to a sticker stored on the Telegram servers.
 * By default, this sticker will be sent by the user.
 * Alternatively, you can use input_message_content
 * to send a message with the specified content instead of the sticker.
 *
 * @apiNote This will only work in Telegram versions
 * released after 9 April, 2016 for static stickers
 * and after 06 July, 2019 for <a href="https://telegram.org/blog/animated-stickers">animated stickers</a>.
 * Older clients will ignore them.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InlineQueryResultCachedSticker implements InlineQueryResult {
    /**
     * Type of the result, must be sticker
     */
    @JsonProperty("type")
    private String type;

    /**
     * Unique identifier for this result, 1-64 bytes
     */
    @JsonProperty("id")
    private String id;

    /**
     * A valid file identifier of the sticker
     */
    @JsonProperty("sticker_file_id")
    private String stickerFileId;

    /**
     * Optional. <a href="https://core.telegram.org/bots/features#inline-keyboards">Inline keyboard</a> attached to the message
     */
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;

    /**
     * Optional. Content of the message to be sent instead of the sticker
     */
    @JsonProperty("input_message_content")
    private InputMessageContent inputMessageContent;
}
