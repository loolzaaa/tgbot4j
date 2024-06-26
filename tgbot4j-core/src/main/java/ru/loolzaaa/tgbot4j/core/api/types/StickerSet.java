package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object represents a sticker set.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StickerSet {
    /**
     * Sticker set name
     */
    @JsonProperty("name")
    private String name;

    /**
     * Sticker set title
     */
    @JsonProperty("title")
    private String title;

    /**
     * Type of stickers in the set, currently one of “regular”, “mask”, “custom_emoji”
     */
    @JsonProperty("sticker_type")
    private String stickerType;

    /**
     * List of all set stickers
     */
    @JsonProperty("stickers")
    private List<Sticker> stickers;

    /**
     * Optional. Sticker set thumbnail in the .WEBP, .TGS, or .WEBM format
     */
    @JsonProperty("thumbnail")
    private PhotoSize thumbnail;
}
