package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object contains information
 * about the chat whose identifier
 * was shared with the bot
 * using a {@link KeyboardButtonRequestChat} button.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatShared {
    /**
     * Identifier of the request
     */
    @JsonProperty("request_id")
    private Integer requestId;

    /**
     * Identifier of the shared chat.
     * This number may have more than 32 significant bits
     * and some programming languages may have difficulty/silent
     * defects in interpreting it. But it has at most 52 significant bits,
     * so a 64-bit integer or double-precision float type
     * are safe for storing this identifier.
     * The bot may not have access to the chat
     * and could be unable to use this identifier,
     * unless the chat is already known to the bot by some other means.
     */
    @JsonProperty("chat_id")
    private Long chatId;

    /**
     * Optional. Title of the chat, if the title
     * was requested by the bot.
     */
    @JsonProperty("title")
    private String title;

    /**
     * Optional. Username of the chat, if the username
     * was requested by the bot and available.
     */
    @JsonProperty("username")
    private String username;

    /**
     * Optional. Available sizes of the chat photo,
     * if the photo was requested by the bot
     */
    @JsonProperty("photo")
    private List<PhotoSize> photo;
}
