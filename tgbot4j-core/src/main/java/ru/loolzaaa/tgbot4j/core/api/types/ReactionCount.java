package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a reaction added to a message
 * along with the number of times it was added.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactionCount {
    /**
     * Type of the reaction
     */
    @JsonProperty("type")
    private ReactionType type;

    /**
     * Number of times the reaction was added
     */
    @JsonProperty("total_count")
    private Integer totalCount;
}
