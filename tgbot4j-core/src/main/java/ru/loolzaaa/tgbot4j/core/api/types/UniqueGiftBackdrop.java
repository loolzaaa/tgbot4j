package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object describes the backdrop of a unique gift.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniqueGiftBackdrop {
    /**
     * Name of the backdrop
     */
    @JsonProperty("name")
    private String name;

    /**
     * Colors of the backdrop
     */
    @JsonProperty("colors")
    private UniqueGiftBackdropColors colors;

    /**
     * The number of unique gifts that receive this backdrop
     * for every 1000 gifts upgraded
     */
    @JsonProperty("rarity_per_mille")
    private Integer rarityPerMille;
}
