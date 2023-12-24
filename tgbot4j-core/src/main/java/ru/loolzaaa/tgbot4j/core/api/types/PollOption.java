package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object contains information about one answer option in a poll.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PollOption {
    /**
     * Option text, 1-100 characters
     */
    @JsonProperty("text")
    private String text;

    /**
     * Number of users that voted for this option
     */
    @JsonProperty("voter_count")
    private Integer voterCount;
}
