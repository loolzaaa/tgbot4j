package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a <a href="https://telegram.org/blog/video-messages-and-telescope">video message</a>
 * (available in Telegram apps as of <a href="https://telegram.org/blog/video-messages-and-telescope">v.4.0</a>).
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoNote {
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
     * Video width and height (diameter of the video message) as defined by sender
     */
    @JsonProperty("length")
    private Integer length;

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
     * Optional. File size in bytes
     */
    @JsonProperty("file_size")
    private Long fileSize;
}
