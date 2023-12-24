package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an issue with one of the files
 * that constitute the translation of a document.
 * The error is considered resolved when the file changes.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassportElementErrorTranslationFile implements PassportElementError {
    /**
     * Error source, must be translation_file
     */
    @JsonProperty("source")
    private String source;

    /**
     * Type of element of the user's Telegram Passport
     * which has the issue, one of “passport”, “driver_license”,
     * “identity_card”, “internal_passport”, “utility_bill”,
     * “bank_statement”, “rental_agreement”, “passport_registration”,
     * “temporary_registration”
     */
    @JsonProperty("type")
    private String type;

    /**
     * Base64-encoded file hash
     */
    @JsonProperty("file_hash")
    private String fileHash;

    /**
     * Error message
     */
    @JsonProperty("message")
    private String message;
}
