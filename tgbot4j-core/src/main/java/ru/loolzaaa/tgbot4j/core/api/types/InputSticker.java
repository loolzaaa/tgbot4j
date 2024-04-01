package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object describes a sticker to be added to a sticker set.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputSticker {
    /**
     * The added sticker. Pass a file_id as a String to send a file
     * that already exists on the Telegram servers,
     * pass an HTTP URL as a String for Telegram to get a file from the Internet,
     * upload a new one using multipart/form-data,
     * or pass “attach://<file_attach_name>” to upload a new one
     * using multipart/form-data under <file_attach_name> name.
     * Animated and video stickers can't be uploaded via HTTP URL.
     * <a href="https://core.telegram.org/bots/api#sending-files">More information on Sending Files »</a>
     */
    @JsonProperty("sticker")
    private InputFile sticker;

    /**
     * Format of the added sticker, must be one of “static”
     * for a <b>.WEBP</b> or <b>.PNG</b> image, “animated”
     * for a <b>.TGS</b> animation, “video” for a <b>WEBM</b> video
     */
    @JsonProperty("format")
    private String format;

    /**
     * List of 1-20 emoji associated with the sticker
     */
    @JsonProperty("emoji_list")
    private List<String> emojiList;

    /**
     * Optional. Position where the mask should be placed on faces.
     * For “mask” stickers only.
     */
    @JsonProperty("mask_position")
    private MaskPosition maskPosition;

    /**
     * Optional. List of 0-20 search keywords for the sticker
     * with total length of up to 64 characters.
     * For “regular” and “custom_emoji” stickers only.
     */
    @JsonProperty("keywords")
    private List<String> keywords;
}
