package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The reaction is paid.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactionTypePaid {
    /**
     * Type of the reaction, always “paid”
     */
    @JsonProperty("type")
    private String type;
}
