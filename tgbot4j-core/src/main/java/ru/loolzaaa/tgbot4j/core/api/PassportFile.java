package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a file uploaded to Telegram Passport.
 * Currently all Telegram Passport files are in JPEG format
 * when decrypted and don't exceed 10MB.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassportFile {
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
     * File size in bytes
     */
    @JsonProperty("file_size")
    private Integer fileSize;

    /**
     * Unix time when the file was uploaded
     */
    @JsonProperty("file_date")
    private Integer fileDate;
}
