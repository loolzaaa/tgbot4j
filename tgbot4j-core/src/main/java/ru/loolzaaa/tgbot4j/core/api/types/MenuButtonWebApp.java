package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.methods.AnswerWebAppQuery;

/**
 * Represents a menu button, which launches a <a href="https://core.telegram.org/bots/webapps">Web App</a>.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuButtonWebApp implements MenuButton {
    /**
     * Type of the button, must be web_app
     */
    @JsonProperty("type")
    private String type;

    /**
     * Text on the button
     */
    @JsonProperty("text")
    private String text;

    /**
     * Description of the Web App that will be launched
     * when the user presses the button. The Web App will be able
     * to send an arbitrary message on behalf of the user
     * using the method {@link AnswerWebAppQuery}.
     * <p>
     * Alternatively, a {@code t.me} link to a Web App of the bot
     * can be specified in the object instead of the Web App's URL,
     * in which case the Web App will be opened
     * as if the user pressed the link.
     */
    @JsonProperty("web_app")
    private WebAppInfo webApp;
}
