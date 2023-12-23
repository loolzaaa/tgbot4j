package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents one size of a photo or a {@link Document} / {@link Sticker} thumbnail.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoSize {
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
     * Photo width
     */
    @JsonProperty("width")
    private Integer width;

    /**
     * Photo height
     */
    @JsonProperty("height")
    private Integer height;

    /**
     * Optional. File size in bytes
     */
    @JsonProperty("file_size")
    private Integer fileSize;
}
