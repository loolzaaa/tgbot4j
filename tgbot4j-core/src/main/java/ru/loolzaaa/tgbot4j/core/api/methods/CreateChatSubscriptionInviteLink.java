package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.ChatInviteLink;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to create a subscription invite link for a channel chat.
 * The bot must have the can_invite_users administrator rights.
 * The link can be edited using the method {@link EditChatSubscriptionInviteLink}
 * or revoked using the method revokeChatInviteLink.
 * Returns the new invite link as a {@link ChatInviteLink} object.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateChatSubscriptionInviteLink implements TelegramMethod<ChatInviteLink> {
    /**
     * Unique identifier for the target group or username
     * of the target supergroup or channel (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Invite link name; 0-32 characters
     */
    @JsonProperty("name")
    private String name;

    /**
     * The number of seconds the subscription will be active
     * for before the next payment.
     * Currently, it must always be 2592000 (30 days).
     */
    @Required
    @JsonProperty("subscription_period")
    private Integer subscriptionPeriod;

    /**
     * The amount of Telegram Stars a user must pay initially
     * and after each subsequent subscription period
     * to be a member of the chat; 1-10000
     */
    @Required
    @JsonProperty("subscription_price")
    private Integer subscriptionPrice;

    @Override
    public ChatInviteLink determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, ChatInviteLink.class);
    }

    @Override
    public void validate() {
        if (chatId == null) {
            throw new ApiValidationException("Chat ID parameter can't be null", this);
        }
        if (name != null && name.length() > 32) {
            throw new ApiValidationException("Name parameter can't be null", this);
        }
        if (subscriptionPeriod == null) {
            throw new ApiValidationException("SubscriptionPeriod parameter can't be null", this);
        }
        if (subscriptionPrice == null) {
            throw new ApiValidationException("SubscriptionPrice parameter can't be null", this);
        }
        if (subscriptionPeriod != 2592000) {
            throw new ApiValidationException("SubscriptionPeriod parameter should be exact 2592000", this);
        }
        if (subscriptionPrice < 1 || subscriptionPrice > 10000) {
            throw new ApiValidationException("SubscriptionPrice parameter should be from 1 to 10000", this);
        }
    }
}
