package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a service message about the chat owner leaving the chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatOwnerLeft {
    /**
     * Optional. The user which will be the new owner
     * of the chat if the previous owner does not return to the chat
     */
    @JsonProperty("new_owner")
    private User newOwner;
}
