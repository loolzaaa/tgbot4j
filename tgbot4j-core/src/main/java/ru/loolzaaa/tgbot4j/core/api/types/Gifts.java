package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object represent a list of gifts.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gifts {
    /**
     * The list of gifts
     */
    @JsonProperty("gifts")
    private List<Gift> gifts;
}
