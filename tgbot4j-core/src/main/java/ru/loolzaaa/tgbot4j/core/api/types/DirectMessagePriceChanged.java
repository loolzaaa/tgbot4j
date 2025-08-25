package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a service message about a change
 * in the price of direct messages sent to a channel chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectMessagePriceChanged {
    /**
     * True, if direct messages are enabled
     * for the channel chat; false otherwise
     */
    @JsonProperty("are_direct_messages_enabled")
    private Boolean areDirectMessagesEnabled;

    /**
     * Optional. The new number of Telegram Stars
     * that must be paid by users for each direct message sent
     * to the channel. Does not apply to users
     * who have been exempted by administrators.
     * Defaults to 0.
     */
    @JsonProperty("direct_message_star_count")
    private Integer directMessageStarCount;
}
