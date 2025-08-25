package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object describes a unique gift that was upgraded
 * from a regular gift.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniqueGift {
    /**
     * Human-readable name of the regular gift
     * from which this unique gift was upgraded
     */
    @JsonProperty("base_name")
    private String baseName;

    /**
     * Unique name of the gift. This name can be used
     * in {@code https://t.me/nft/...} links and story areas
     */
    @JsonProperty("name")
    private String name;

    /**
     * Unique number of the upgraded gift among gifts upgraded
     * from the same regular gift
     */
    @JsonProperty("number")
    private Integer number;

    /**
     * Model of the gift
     */
    @JsonProperty("model")
    private UniqueGiftModel model;

    /**
     * Symbol of the gift
     */
    @JsonProperty("symbol")
    private UniqueGiftSymbol symbol;

    /**
     * Backdrop of the gift
     */
    @JsonProperty("backdrop")
    private UniqueGiftBackdrop backdrop;

    /**
     * Optional. Information about the chat that published the gift
     */
    @JsonProperty("publisher_chat")
    private Chat publisherChat;
}
