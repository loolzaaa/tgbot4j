package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents an issue with a list of scans.
 * The error is considered resolved
 * when the list of files containing the scans changes.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassportElementErrorFiles implements PassportElementError {
    /**
     * Error source, must be files
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
     * List of base64-encoded file hashes
     */
    @JsonProperty("file_hashes")
    private List<String> fileHashes;

    /**
     * Error message
     */
    @JsonProperty("message")
    private String message;
}
