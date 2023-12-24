package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes that no specific value for the menu button was set.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuButtonDefault {
    /**
     * Type of the button, must be default
     */
    @JsonProperty("type")
    private String type;
}
