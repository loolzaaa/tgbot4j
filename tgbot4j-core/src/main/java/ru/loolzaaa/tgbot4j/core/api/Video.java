package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a video file.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {
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
     * Video width as defined by sender
     */
    @JsonProperty("width")
    private Integer width;

    /**
     * Video height as defined by sender
     */
    @JsonProperty("height")
    private Integer height;

    /**
     * Duration of the video in seconds as defined by sender
     */
    @JsonProperty("duration")
    private Integer duration;

    /**
     * Optional. Video thumbnail
     */
    @JsonProperty("thumbnail")
    private PhotoSize thumbnail;

    /**
     * Optional. Original filename as defined by sender
     */
    @JsonProperty("file_name")
    private String fileName;

    /**
     * Optional. MIME type of the file as defined by sender
     */
    @JsonProperty("mime_type")
    private String mimeType;

    /**
     * Optional. File size in bytes.
     * It can be bigger than 2^31 and some programming languages
     * may have difficulty/silent defects in interpreting it.
     * But it has at most 52 significant bits,
     * so a signed 64-bit integer or double-precision float type
     * are safe for storing this value.
     */
    @JsonProperty("file_size")
    private Long fileSize;
}
