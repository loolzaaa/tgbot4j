package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an issue in an unspecified place.
 * The error is considered resolved when new data is added.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassportElementErrorUnspecified {
    /**
     * Error source, must be unspecified
     */
    @JsonProperty("source")
    private String source;

    /**
     * Type of element of the user's Telegram Passport which has the issue
     */
    @JsonProperty("type")
    private String type;

    /**
     * Base64-encoded element hash
     */
    @JsonProperty("element_hash")
    private String elementHash;

    /**
     * Error message
     */
    @JsonProperty("message")
    private String message;
}
