package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a <a href="https://core.telegram.org/bots/webapps">Web App</a>.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebAppInfo {
    /**
     * An HTTPS URL of a Web App to be opened with additional data
     * as specified in <a href="https://core.telegram.org/bots/webapps#initializing-mini-apps">Initializing Web Apps</a>
     */
    @JsonProperty("url")
    private String url;
}
