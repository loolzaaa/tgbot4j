package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the {@link InputMessageContent} of a contact message
 * to be sent as the result of an inline query.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputContactMessageContent {
    /**
     * Contact's phone number
     */
    @JsonProperty("phone_number")
    private String phoneNumber;

    /**
     * Contact's first name
     */
    @JsonProperty("first_name")
    private String firstName;

    /**
     * Optional. Contact's last name
     */
    @JsonProperty("last_name")
    private String lastName;

    /**
     * Optional. Additional data about the contact
     * in the form of a <a href="https://en.wikipedia.org/wiki/VCard">vCard</a>, 0-2048 bytes
     */
    @JsonProperty("vcard")
    private String vcard;
}
