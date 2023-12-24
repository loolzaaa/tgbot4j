package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an issue in one of the data fields
 * that was provided by the user. The error is considered
 * resolved when the field's value changes.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassportElementErrorDataField implements PassportElementError {
    /**
     * Error source, must be data
     */
    @JsonProperty("source")
    private String source;

    /**
     * The section of the user's Telegram Passport
     * which has the error, one of “personal_details”,
     * “passport”, “driver_license”, “identity_card”,
     * “internal_passport”, “address”
     */
    @JsonProperty("type")
    private String type;

    /**
     * Name of the data field which has the error
     */
    @JsonProperty("field_name")
    private String fieldName;

    /**
     * Base64-encoded data hash
     */
    @JsonProperty("data_hash")
    private String dataHash;

    /**
     * Error message
     */
    @JsonProperty("message")
    private String message;
}
