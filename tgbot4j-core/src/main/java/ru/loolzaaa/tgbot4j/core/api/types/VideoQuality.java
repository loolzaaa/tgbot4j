package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a video file of a specific quality.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoQuality {
    /**
     * Identifier for this file, which can be used
     * to download or reuse the file
     */
    @JsonProperty("file_id")
    private String fileId;

    /**
     * Unique identifier for this file, which is supposed
     * to be the same over time and for different bots.
     * Can't be used to download or reuse the file.
     */
    @JsonProperty("file_unique_id")
    private String fileUniqueId;

    /**
     * Video width
     */
    @JsonProperty("width")
    private Integer width;

    /**
     * Video height
     */
    @JsonProperty("height")
    private Integer height;

    /**
     * Codec that was used to encode the video,
     * for example, “h264”, “h265”, or “av01”
     */
    @JsonProperty("codec")
    private String codec;

    /**
     * File size in bytes. It can be bigger than 2^31
     * and some programming languages may have difficulty/silent
     * defects in interpreting it. But it has at most 52 significant bits,
     * so a signed 64-bit integer or double-precision float type
     * are safe for storing this value.
     */
    @JsonProperty("file_size")
    private Long fileSize;
}
