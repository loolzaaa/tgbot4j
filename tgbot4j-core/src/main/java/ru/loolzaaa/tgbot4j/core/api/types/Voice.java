package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a voice note.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voice {
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
     * 	Duration of the audio in seconds as defined by the sender
     */
    @JsonProperty("duration")
    private Integer duration;

    /**
     * Optional. MIME type of the file as defined by the sender
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
