package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Describes Telegram Passport data shared with the bot by the user.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassportData {
    /**
     * Array with information about documents
     * and other Telegram Passport elements
     * that was shared with the bot
     */
    @JsonProperty("data")
    private List<EncryptedPassportElement> data;

    /**
     * Encrypted credentials required to decrypt the data
     */
    @JsonProperty("credentials")
    private EncryptedCredentials credentials;
}
