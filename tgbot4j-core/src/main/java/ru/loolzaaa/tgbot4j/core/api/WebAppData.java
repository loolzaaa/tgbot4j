package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes data sent from a <a href="https://core.telegram.org/bots/webapps">Web App</a> to the bot.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebAppData {
    /**
     * The data. Be aware that a bad client
     * can send arbitrary data in this field.
     */
    @JsonProperty("data")
    private String data;

    /**
     * Text of the web_app keyboard button from which
     * the Web App was opened. Be aware that a bad client
     * can send arbitrary data in this field.
     */
    @JsonProperty("button_text")
    private String buttonText;
}
