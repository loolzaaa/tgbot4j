package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes an inline message to be sent by a user of a Mini App.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreparedInlineMessage {
    /**
     * Unique identifier of the prepared message
     */
    @JsonProperty("id")
    private String id;

    /**
     * Expiration date of the prepared message, in Unix time.
     * Expired prepared messages can no longer be used
     */
    @JsonProperty("expiration_date")
    private Integer expirationDate;
}
