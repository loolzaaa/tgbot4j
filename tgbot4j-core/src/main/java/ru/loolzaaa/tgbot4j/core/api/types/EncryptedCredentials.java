package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes data required for decrypting and authenticating
 * {@link EncryptedPassportElement}. See the <a href="https://core.telegram.org/passport#receiving-information">Telegram Passport Documentation</a>
 * for a complete description of the data decryption
 * and authentication processes.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EncryptedCredentials {
    /**
     * Base64-encoded encrypted JSON-serialized data
     * with unique user's payload, data hashes and secrets
     * required for {@link EncryptedPassportElement} decryption
     * and authentication
     */
    @JsonProperty("data")
    private String data;

    /**
     * Base64-encoded data hash for data authentication
     */
    @JsonProperty("hash")
    private String hash;

    /**
     * Base64-encoded secret, encrypted with the bot's public RSA key,
     * required for data decryption
     */
    @JsonProperty("secret")
    private String secret;
}
