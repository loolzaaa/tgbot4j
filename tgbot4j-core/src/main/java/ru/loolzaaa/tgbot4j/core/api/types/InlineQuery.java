package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents an incoming inline query.
 * When the user sends an empty query, your bot
 * could return some default or trending results.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InlineQuery {
    /**
     * Unique identifier for this query
     */
    @JsonProperty("id")
    private String id;

    /**
     * Sender
     */
    @JsonProperty("from")
    private User from;

    /**
     * Text of the query (up to 256 characters)
     */
    @JsonProperty("query")
    private String query;

    /**
     * Offset of the results to be returned,
     * can be controlled by the bot
     */
    @JsonProperty("offset")
    private String offset;

    /**
     * Optional. Type of the chat from which the inline query was sent.
     * Can be either “sender” for a private chat with the inline query sender,
     * “private”, “group”, “supergroup”, or “channel”.
     * The chat type should be always known for requests sent
     * from official clients and most third-party clients,
     * unless the request was sent from a secret chat
     */
    @JsonProperty("chat_type")
    private String chatType;

    /**
     * Optional. Sender location, only for bots that request user location
     */
    @JsonProperty("location")
    private Location location;
}
