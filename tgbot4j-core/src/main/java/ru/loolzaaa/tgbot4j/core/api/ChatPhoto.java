package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a chat photo.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatPhoto {
    /**
     * File identifier of small (160x160) chat photo.
     * This file_id can be used only for photo download
     * and only for as long as the photo is not changed.
     */
    @JsonProperty("small_file_id")
    private String smallFileId;

    /**
     * Unique file identifier of small (160x160) chat photo,
     * which is supposed to be the same over time
     * and for different bots.
     * Can't be used to download or reuse the file.
     */
    @JsonProperty("small_file_unique_id")
    private String smallFileUniqueId;

    /**
     * File identifier of big (640x640) chat photo.
     * This file_id can be used only for photo download
     * and only for as long as the photo is not changed.
     */
    @JsonProperty("big_file_id")
    private String bigFileId;

    /**
     * Unique file identifier of big (640x640) chat photo,
     * which is supposed to be the same over time
     * and for different bots.
     * Can't be used to download or reuse the file.
     */
    @JsonProperty("big_file_unique_id")
    private String bigFileUniqueId;
}
