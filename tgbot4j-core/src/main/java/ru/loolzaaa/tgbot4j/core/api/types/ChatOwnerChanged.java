package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a service message about an ownership change in the chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatOwnerChanged {
    /**
     * The new owner of the chat
     */
    @JsonProperty("new_owner")
    private User newOwner;
}
