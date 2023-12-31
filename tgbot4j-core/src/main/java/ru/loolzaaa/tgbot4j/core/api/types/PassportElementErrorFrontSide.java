package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an issue with the front side of a document.
 * The error is considered resolved when the file
 * with the front side of the document changes.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassportElementErrorFrontSide implements PassportElementError {
    /**
     * Error source, must be front_side
     */
    @JsonProperty("source")
    private String source;

    /**
     * The section of the user's Telegram Passport
     * which has the issue, one of “passport”,
     * “driver_license”, “identity_card”,
     * “internal_passport”
     */
    @JsonProperty("type")
    private String type;

    /**
     * Base64-encoded hash of the file with the front side of the document
     */
    @JsonProperty("file_hash")
    private String fileHash;

    /**
     * Error message
     */
    @JsonProperty("message")
    private String message;
}
