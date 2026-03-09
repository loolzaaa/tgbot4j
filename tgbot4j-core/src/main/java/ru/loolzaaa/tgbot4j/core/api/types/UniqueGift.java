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
     * Identifier of the regular gift from which the gift was upgraded
     */
    @JsonProperty("gift_id")
    private String giftId;

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
     * Optional. True, if the original regular gift was
     * exclusively purchaseable by Telegram Premium subscribers
     */
    @JsonProperty("is_premium")
    private Boolean isPremium;

    /**
     * Optional. True, if the gift is assigned from the TON blockchain
     * and can't be resold or transferred in Telegram
     */
    @JsonProperty("is_from_blockchain")
    private Boolean isFromBlockchain;

    /**
     * Optional. The color scheme that can be used by the gift's owner
     * for the chat's name, replies to messages and link previews;
     * for business account gifts and gifts that are currently on sale only
     */
    @JsonProperty("colors")
    private UniqueGiftColors colors;

    /**
     * Optional. Information about the chat that published the gift
     */
    @JsonProperty("publisher_chat")
    private Chat publisherChat;
}
