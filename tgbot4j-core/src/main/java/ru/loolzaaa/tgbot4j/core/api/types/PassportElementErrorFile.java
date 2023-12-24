package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an issue with a document scan.
 * The error is considered resolved
 * when the file with the document scan changes.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassportElementErrorFile {
    /**
     * Error source, must be file
     */
    @JsonProperty("source")
    private String source;

    /**
     * The section of the user's Telegram Passport
     * which has the issue, one of “utility_bill”,
     * “bank_statement”, “rental_agreement”,
     * “passport_registration”, “temporary_registration”
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
