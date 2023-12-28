package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.methods.GetFile;

/**
 * This object represents a file ready to be downloaded.
 * The file can be downloaded via the link {@code https://api.telegram.org/file/bot<token>/<file_path>}.
 * It is guaranteed that the link will be valid for at least 1 hour.
 * When the link expires, a new one can be requested by calling {@link GetFile}.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class File {
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
     * Optional. File size in bytes. It can be bigger than 2^31 and
     * some programming languages may have difficulty/silent defects in interpreting it.
     * But it has at most 52 significant bits,
     * so a signed 64-bit integer or double-precision float type
     * are safe for storing this value.
     */
    @JsonProperty("file_size")
    private Integer fileSize;

    /**
     * Optional. File path. Use {@code https://api.telegram.org/file/bot<token>/<file_path>} to get the file.
     */
    @JsonProperty("file_path")
    private String filePath;
}
