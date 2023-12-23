package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents an inline button
 * that switches the current user to inline mode
 * in a chosen chat, with an optional default inline query.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwitchInlineQueryChosenChat {
    /**
     * Optional. The default inline query to be inserted
     * in the input field. If left empty,
     * only the bot's username will be inserted
     */
    @JsonProperty("query")
    private String query;

    /**
     * Optional. True, if private chats with users can be chosen
     */
    @JsonProperty("allow_user_chats")
    private Boolean allowUserChats;

    /**
     * Optional. True, if private chats with bots can be chosen
     */
    @JsonProperty("allow_bot_chats")
    private Boolean allowBotChats;

    /**
     * Optional. True, if group and supergroup chats can be chosen
     */
    @JsonProperty("allow_group_chats")
    private Boolean allowGroupChats;

    /**
     * Optional. True, if channel chats can be chosen
     */
    @JsonProperty("allow_channel_chats")
    private Boolean allowChannelChats;
}
