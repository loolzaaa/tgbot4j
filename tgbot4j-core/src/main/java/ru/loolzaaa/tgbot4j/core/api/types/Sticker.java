package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a sticker.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sticker {
    /**
     * Identifier for this file, which can be used to download or reuse the file
     */
    @JsonProperty("file_id")
    private String fileId;

    /**
     * Unique identifier for this file, which is supposed to be
     * the same over time and for different bots.
     * Can't be used to download or reuse the file.
     */
    @JsonProperty("file_unique_id")
    private String fileUniqueId;

    /**
     * Type of the sticker, currently one of “regular”, “mask”,
     * “custom_emoji”. The type of the sticker is independent from its format,
     * which is determined by the fields <i>is_animated</i> and <i>is_video</i>.
     */
    @JsonProperty("type")
    private String type;

    /**
     * Sticker width
     */
    @JsonProperty("width")
    private Integer width;

    /**
     * Sticker height
     */
    @JsonProperty("height")
    private Integer height;

    /**
     * True, if the sticker is <a href="https://telegram.org/blog/animated-stickers">animated</a>
     */
    @JsonProperty("is_animated")
    private Boolean isAnimated;

    /**
     * True, if the sticker is a <a href="https://telegram.org/blog/video-stickers-better-reactions">video sticker</a>
     */
    @JsonProperty("is_video")
    private Boolean isVideo;

    /**
     * Optional. Sticker thumbnail in the .WEBP or .JPG format
     */
    @JsonProperty("thumbnail")
    private PhotoSize thumbnail;

    /**
     * Optional. Emoji associated with the sticker
     */
    @JsonProperty("emoji")
    private String emoji;

    /**
     * Optional. Name of the sticker set to which the sticker belongs
     */
    @JsonProperty("set_name")
    private String setName;

    /**
     * Optional. For premium regular stickers, premium animation for the sticker
     */
    @JsonProperty("premium_animation")
    private File premiumAnimation;

    /**
     * Optional. For mask stickers, the position where the mask should be placed
     */
    @JsonProperty("mask_position")
    private MaskPosition maskPosition;

    /**
     * Optional. For custom emoji stickers, unique identifier of the custom emoji
     */
    @JsonProperty("custom_emoji_id")
    private String customEmojiId;

    /**
     * Optional. True, if the sticker must be repainted to a text color in messages,
     * the color of the Telegram Premium badge in emoji status,
     * white color on chat photos, or another appropriate color in other places
     */
    @JsonProperty("needs_repainting")
    private Boolean needsRepainting;

    /**
     * Optional. File size in bytes
     */
    @JsonProperty("file_size")
    private Integer fileSize;
}
