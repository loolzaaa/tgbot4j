package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a transaction with a chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionPartnerChat implements TransactionPartner {
    /**
     * Type of the transaction partner, always “chat”
     */
    @JsonProperty("type")
    private String type;

    /**
     * Information about the chat
     */
    @JsonProperty("chat")
    private Chat chat;

    /**
     * Optional. The gift sent to the chat by the bot
     */
    @JsonProperty("gift")
    private Gift gift;
}
