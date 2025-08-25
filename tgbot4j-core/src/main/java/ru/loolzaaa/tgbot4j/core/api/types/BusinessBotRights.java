package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the rights of a business bot.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessBotRights {
    /**
     * Optional. True, if the bot can send and edit messages
     * in the private chats that had incoming messages
     * in the last 24 hours
     */
    @JsonProperty("can_reply")
    private Boolean canReply;

    /**
     * Optional. True, if the bot can mark incoming
     * private messages as read
     */
    @JsonProperty("can_read_messages")
    private Boolean canReadMessages;

    /**
     * Optional. True, if the bot can delete messages sent by the bot
     */
    @JsonProperty("can_delete_sent_messages")
    private Boolean canDeleteSentMessages;

    /**
     * Optional. True, if the bot can delete all private messages
     * in managed chats
     */
    @JsonProperty("can_delete_all_messages")
    private Boolean canDeleteAllMessages;

    /**
     * Optional. True, if the bot can edit the first
     * and last name of the business account
     */
    @JsonProperty("can_edit_name")
    private Boolean canEditName;

    /**
     * Optional. True, if the bot can edit the bio
     * of the business account
     */
    @JsonProperty("can_edit_bio")
    private Boolean canEditBio;

    /**
     * Optional. True, if the bot can edit the profile photo
     * of the business account
     */
    @JsonProperty("can_edit_profile_photo")
    private Boolean canEditProfilePhoto;

    /**
     * Optional. True, if the bot can edit the username
     * of the business account
     */
    @JsonProperty("can_edit_username")
    private Boolean canEditUsername;

    /**
     * Optional. True, if the bot can change the privacy settings
     * pertaining to gifts for the business account
     */
    @JsonProperty("can_change_gift_settings")
    private Boolean canChangeGiftSettings;

    /**
     * Optional. True, if the bot can view gifts and the amount
     * of Telegram Stars owned by the business account
     */
    @JsonProperty("can_view_gifts_and_stars")
    private Boolean canViewGiftsAndStars;

    /**
     * Optional. True, if the bot can convert regular gifts
     * owned by the business account to Telegram Stars
     */
    @JsonProperty("can_convert_gifts_to_stars")
    private Boolean canConvertGiftsToStars;

    /**
     * Optional. True, if the bot can transfer and upgrade gifts
     * owned by the business account
     */
    @JsonProperty("can_transfer_and_upgrade_gifts")
    private Boolean canTransferAndUpgradeGifts;

    /**
     * Optional. True, if the bot can transfer Telegram Stars
     * received by the business account to its own account,
     * or use them to upgrade and transfer gifts
     */
    @JsonProperty("can_transfer_stars")
    private Boolean canTransferStars;

    /**
     * Optional. True, if the bot can post, edit
     * and delete stories on behalf of the business account
     */
    @JsonProperty("can_manage_stories")
    private Boolean canManageStories;
}
