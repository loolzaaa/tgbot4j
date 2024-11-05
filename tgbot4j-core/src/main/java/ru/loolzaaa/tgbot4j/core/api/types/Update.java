package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This <a href="https://core.telegram.org/bots/api#available-types">object</a> represents an incoming update.
 *
 * @apiNote At most <b>one</b> of the optional parameters can be present in any given update.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Update {
    /**
     * The update's unique identifier.
     * Update identifiers start from a certain positive number
     * and increase sequentially. This ID becomes especially handy
     * if you're using <a href="https://core.telegram.org/bots/api#setwebhook">webhooks</a>,
     * since it allows you to ignore repeated updates
     * or to restore the correct update sequence,
     * should they get out of order. If there are no new updates
     * for at least a week, then identifier of the next update
     * will be chosen randomly instead of sequentially.
     */
    @JsonProperty("update_id")
    private Integer updateId;

    /**
     * Optional. New incoming message of any kind - text,
     * photo, sticker, etc.
     */
    @JsonProperty("message")
    private Message message;

    /**
     * Optional. New version of a message that is known
     * to the bot and was edited
     */
    @JsonProperty("edited_message")
    private Message editedMessage;

    /**
     * Optional. New incoming channel post of any kind - text,
     * photo, sticker, etc.
     */
    @JsonProperty("channel_post")
    private Message channelPost;

    /**
     * Optional. New version of a channel post that is known
     * to the bot and was edited
     */
    @JsonProperty("edited_channel_post")
    private Message editedChannelPost;

    /**
     * Optional. The bot was connected to or disconnected
     * from a business account, or a user edited
     * an existing connection with the bot
     */
    @JsonProperty("business_connection")
    private BusinessConnection businessConnection;

    /**
     * Optional. New message from a connected
     * business account
     */
    @JsonProperty("business_message")
    private Message businessMessage;

    /**
     * Optional. New version of a message
     * from a connected business account
     */
    @JsonProperty("edited_business_message")
    private Message editedBusinessMessage;

    /**
     * Optional. Messages were deleted from a connected
     * business account
     */
    @JsonProperty("deleted_business_messages")
    private BusinessMessagesDeleted deletedBusinessMessages;

    /**
     * Optional. A reaction to a message was changed by a user.
     * The bot must be an administrator in the chat
     * and must explicitly specify {@code "message_reaction"}
     * in the list of allowed_updates to receive these updates.
     * The update isn't received for reactions set by bots.
     */
    @JsonProperty("message_reaction")
    private MessageReactionUpdated messageReaction;

    /**
     * Optional. Reactions to a message with anonymous reactions
     * were changed. The bot must be an administrator in the chat
     * and must explicitly specify {@code "message_reaction_count"}
     * in the list of allowed_updates to receive these updates.
     */
    @JsonProperty("message_reaction_count")
    private MessageReactionCountUpdated messageReactionCount;

    /**
     * Optional. New incoming <a href="https://core.telegram.org/bots/api#inline-mode">inline</a> query
     */
    @JsonProperty("inline_query")
    private InlineQuery inlineQuery;

    /**
     * Optional. The result of an <a href="https://core.telegram.org/bots/api#inline-mode">inline</a> query that was chosen
     * by a user and sent to their chat partner.
     * Please see our documentation on the <a href="https://core.telegram.org/bots/inline#collecting-feedback">feedback collecting</a>
     * for details on how to enable these updates for your bot.
     */
    @JsonProperty("chosen_inline_result")
    private ChosenInlineResult chosenInlineResult;

    /**
     * Optional. New incoming callback query
     */
    @JsonProperty("callback_query")
    private CallbackQuery callbackQuery;

    /**
     * Optional. New incoming shipping query.
     * Only for invoices with flexible price
     */
    @JsonProperty("shipping_query")
    private ShippingQuery shippingQuery;

    /**
     * Optional. New incoming pre-checkout query.
     * Contains full information about checkout
     */
    @JsonProperty("pre_checkout_query")
    private PreCheckoutQuery preCheckoutQuery;

    /**
     * Optional. A user purchased paid media with
     * a non-empty payload sent by the bot in a non-channel chat
     */
    @JsonProperty("purchased_paid_media")
    private PaidMediaPurchased purchasedPaidMedia;

    /**
     * Optional. New poll state. Bots receive only updates
     * about stopped polls and polls, which are sent by the bot
     */
    @JsonProperty("poll")
    private Poll poll;

    /**
     * Optional. A user changed their answer in a non-anonymous poll.
     * Bots receive new votes only in polls that were sent by the bot itself.
     */
    @JsonProperty("poll_answer")
    private PollAnswer pollAnswer;

    /**
     * Optional. The bot's chat member status was updated in a chat.
     * For private chats, this update is received only when the bot
     * is blocked or unblocked by the user.
     */
    @JsonProperty("my_chat_member")
    private ChatMemberUpdated myChatMember;

    /**
     * Optional. A chat member's status was updated in a chat.
     * The bot must be an administrator in the chat and must
     * explicitly specify {@code "chat_member"} in the list
     * of allowed_updates to receive these updates.
     */
    @JsonProperty("chat_member")
    private ChatMemberUpdated chatMember;

    /**
     * Optional. A request to join the chat has been sent.
     * The bot must have the can_invite_users administrator right
     * in the chat to receive these updates.
     */
    @JsonProperty("chat_join_request")
    private ChatJoinRequest chatJoinRequest;

    /**
     * Optional. A chat boost was added or changed.
     * The bot must be an administrator in the chat
     * to receive these updates.
     */
    @JsonProperty("chat_boost")
    private ChatBoostUpdated chatBoost;

    /**
     * Optional. A boost was removed from a chat.
     * The bot must be an administrator in the chat
     * to receive these updates.
     */
    @JsonProperty("removed_chat_boost")
    private ChatBoostRemoved removedChatBoost;
}
