package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents type of a poll,
 * which is allowed to be created and sent
 * when the corresponding button is pressed.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyboardButtonPollType {
    /**
     * Optional. If quiz is passed, the user will be allowed
     * to create only polls in the quiz mode.
     * If regular is passed, only regular polls will be allowed.
     * Otherwise, the user will be allowed to create a poll of any type.
     */
    @JsonProperty("type")
    private String type;
}
