package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a unique gift received and owned
 * by a user or a chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnedGiftUnique implements OwnedGift {
    /**
     * Type of the gift, always “unique”
     */
    @JsonProperty("type")
    private String type;

    /**
     * Information about the unique gift
     */
    @JsonProperty("gift")
    private UniqueGift gift;

    /**
     * Optional. Unique identifier of the received gift for the bot;
     * for gifts received on behalf of business accounts only
     */
    @JsonProperty("owned_gift_id")
    private String ownedGiftId;

    /**
     * Optional. Sender of the gift if it is a known user
     */
    @JsonProperty("sender_user")
    private User senderUser;

    /**
     * Date the gift was sent in Unix time
     */
    @JsonProperty("send_date")
    private Integer sendDate;

    /**
     * Optional. True, if the gift is displayed
     * on the account's profile page; for gifts received
     * on behalf of business accounts only
     */
    @JsonProperty("is_saved")
    private Boolean isSaved;

    /**
     * Optional. True, if the gift can be transferred to another owner;
     * for gifts received on behalf of business accounts only
     */
    @JsonProperty("can_be_transferred")
    private Boolean canBeTransferred;

    /**
     * Optional. Number of Telegram Stars that must be paid
     * to transfer the gift; omitted if the bot cannot transfer the gift
     */
    @JsonProperty("transfer_star_count")
    private Integer transferStarCount;

    /**
     * Optional. Point in time (Unix timestamp) when the gift
     * can be transferred. If it is in the past,
     * then the gift can be transferred now
     */
    @JsonProperty("next_transfer_date")
    private Integer nextTransferDate;
}
