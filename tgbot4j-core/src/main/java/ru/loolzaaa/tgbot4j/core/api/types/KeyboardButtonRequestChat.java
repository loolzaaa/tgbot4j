package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object defines the criteria used to request a suitable chat.
 * The identifier of the selected chat will be shared
 * with the bot when the corresponding button is pressed.
 * <a href="https://core.telegram.org/bots/features#chat-and-user-selection">More about requesting chats »</a>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyboardButtonRequestChat {
    /**
     * Signed 32-bit identifier of the request,
     * which will be received back in the {@link ChatShared} object.
     * Must be unique within the message
     */
    @JsonProperty("request_id")
    private Integer requestId;

    /**
     * Pass True to request a channel chat,
     * pass False to request a group or a supergroup chat.
     */
    @JsonProperty("chat_is_channel")
    private Boolean chatIsChannel;

    /**
     * Optional. Pass True to request a forum supergroup,
     * pass False to request a non-forum chat.
     * If not specified, no additional restrictions are applied.
     */
    @JsonProperty("chat_is_forum")
    private Boolean chatIsForum;

    /**
     * Optional. Pass True to request a supergroup
     * or a channel with a username, pass False to request a chat
     * without a username. If not specified,
     * no additional restrictions are applied.
     */
    @JsonProperty("chat_has_username")
    private Boolean chatHasUsername;

    /**
     * Optional. Pass True to request a chat owned by the user.
     * Otherwise, no additional restrictions are applied.
     */
    @JsonProperty("chat_is_created")
    private Boolean chatIsCreated;

    /**
     * Optional. A JSON-serialized object listing the required administrator rights
     * of the user in the chat. The rights must be a superset of bot_administrator_rights.
     * If not specified, no additional restrictions are applied.
     */
    @JsonProperty("user_administrator_rights")
    private ChatAdministratorRights userAdministratorRights;

    /**
     * Optional. A JSON-serialized object listing the required administrator rights
     * of the bot in the chat. The rights must be a subset of user_administrator_rights.
     * If not specified, no additional restrictions are applied.
     */
    @JsonProperty("bot_administrator_rights")
    private ChatAdministratorRights botAdministratorRights;

    /**
     * Optional. Pass True to request a chat with the bot as a member.
     * Otherwise, no additional restrictions are applied.
     */
    @JsonProperty("bot_is_member")
    private Boolean botIsMember;

    /**
     * Optional. Pass True to request the chat's title
     */
    @JsonProperty("request_title")
    private Boolean requestTitle;

    /**
     * Optional. Pass True to request the chat's username
     */
    @JsonProperty("request_username")
    private Boolean requestUsername;

    /**
     * Optional. Pass True to request the chat's photo
     */
    @JsonProperty("request_photo")
    private Boolean requestPhoto;
}
