package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object describes the symbol shown on the pattern
 * of a unique gift.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniqueGiftSymbol {
    /**
     * Name of the model
     */
    @JsonProperty("name")
    private String name;

    /**
     * The sticker that represents the unique gift
     */
    @JsonProperty("sticker")
    private Sticker sticker;

    /**
     * The number of unique gifts that receive this model
     * for every 1000 gifts upgraded
     */
    @JsonProperty("rarity_per_mille")
    private Integer rarityPerMille;
}
