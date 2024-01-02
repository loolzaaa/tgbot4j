package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object represents a list of boosts added
 * to a chat by a user.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserChatBoosts {
    /**
     * The list of boosts added to the chat by the user
     */
    @JsonProperty("boosts")
    private List<ChatBoost> boosts;
}
