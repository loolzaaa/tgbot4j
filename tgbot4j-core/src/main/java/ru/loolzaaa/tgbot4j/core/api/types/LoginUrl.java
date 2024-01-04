package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents a parameter of the inline keyboard button
 * used to automatically authorize a user. Serves as a great replacement
 * for the Telegram Login Widget when the user is coming from Telegram.
 * All the user needs to do is tap/click a button
 * and confirm that they want to log in:
 * <img src="https://core.telegram.org/file/811140015/1734/8VZFkwWXalM.97872/6127fa62d8a0bf2b3c" alt="Open link">
 *
 * @apiNote Telegram apps support these buttons as of version 5.7.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUrl {
    /**
     * An HTTPS URL to be opened with user authorization data added
     * to the query string when the button is pressed.
     * If the user refuses to provide authorization data,
     * the original URL without information about the user will be opened.
     * The data added is the same as described in <a href="https://core.telegram.org/widgets/login#receiving-authorization-data">Receiving authorization data</a>.
     *
     * @apiNote You <b>must</b> always check the hash of the received data
     * to verify the authentication and the integrity of the data
     * as described in <a href="https://core.telegram.org/widgets/login#checking-authorization">Checking authorization</a>.
     */
    @JsonProperty("url")
    private String url;

    /**
     * Optional. New text of the button in forwarded messages.
     */
    @JsonProperty("forward_text")
    private String forwardText;

    /**
     * Optional. Username of a bot, which will be used for user authorization.
     * See <a href="https://core.telegram.org/widgets/login#setting-up-a-bot">Setting up a bot</a>
     * for more details. If not specified,
     * the current bot's username will be assumed.
     * The url's domain must be the same as the domain linked with the bot.
     * See <a href="https://core.telegram.org/widgets/login#linking-your-domain-to-the-bot">Linking your domain to the bot</a> for more details.
     */
    @JsonProperty("bot_username")
    private String botUsername;

    /**
     * Optional. Pass True to request the permission
     * for your bot to send messages to the user.
     */
    @JsonProperty("request_write_access")
    private Boolean requestWriteAccess;
}
