package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.InputFile;
import ru.loolzaaa.tgbot4j.core.api.types.Update;

import java.util.List;

/**
 * Use this method to specify a URL and receive
 * incoming updates via an outgoing webhook.
 * Whenever there is an update for the bot,
 * we will send an HTTPS POST request to the specified URL,
 * containing a JSON-serialized {@link Update}.
 * In case of an unsuccessful request,
 * we will give up after a reasonable amount of attempts.
 * Returns True on success.
 * <p>
 * Notes:
 * <ul>
 *     <li>You will not be able to receive updates using {@link GetUpdates}
 *     for as long as an outgoing webhook is set up.</li>
 *     <li>To use a self-signed certificate,
 *     you need to upload your <a href="https://core.telegram.org/bots/self-signed">public key certificate</a>
 *     using certificate parameter. Please upload as InputFile,
 *     sending a String will not work.</li>
 *     <li>Ports currently supported for webhooks: 443, 80, 88, 8443.</li>
 * </ul>
 * <p>
 * If you're having any trouble setting up webhooks,
 * please check out this <a href="https://core.telegram.org/bots/webhooks">amazing guide to webhooks</a>.
 *
 * @implNote If you'd like to make sure that the webhook
 * was set by you, you can specify secret data
 * in the parameter secret_token. If specified,
 * the request will contain a header “X-Telegram-Bot-Api-Secret-Token”
 * with the secret token as content.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetWebhook implements TelegramMethod<Boolean> {
    /**
     * HTTPS URL to send updates to. Use an empty string
     * to remove webhook integration
     */
    @JsonProperty("url")
    private String url;

    /**
     * Upload your public key certificate so that the root certificate
     * in use can be checked. See our <a href="https://core.telegram.org/bots/self-signed">self-signed guide</a> for details.
     */
    @JsonProperty("certificate")
    private InputFile certificate;

    /**
     * The fixed IP address which will be used to send webhook
     * requests instead of the IP address resolved through DNS
     */
    @JsonProperty("ip_address")
    private String ipAddress;

    /**
     * The maximum allowed number of simultaneous HTTPS connections
     * to the webhook for update delivery, 1-100.
     * Defaults to 40. Use lower values to limit the load
     * on your bot's server, and higher values
     * to increase your bot's throughput.
     */
    @JsonProperty("max_connections")
    private Integer maxConnections;

    /**
     * A JSON-serialized list of the update types you want
     * your bot to receive. For example, specify {@code ["message",
     * "edited_channel_post", "callback_query"]} to only receive
     * updates of these types. See {@link Update} for a complete list
     * of available update types. Specify an empty list
     * to receive all update types except chat_member (default).
     * If not specified, the previous setting will be used.
     */
    @JsonProperty("allowed_updates")
    private List<String> allowedUpdates;

    /**
     * Pass True to drop all pending updates
     */
    @JsonProperty("drop_pending_updates")
    private Boolean dropPendingUpdates;

    /**
     * A secret token to be sent in a header “X-Telegram-Bot-Api-Secret-Token”
     * in every webhook request, 1-256 characters.
     * Only characters {@code A-Z}, {@code a-z}, {@code 0-9},
     * {@code _} and {@code -} are allowed.
     * The header is useful to ensure that the request
     * comes from a webhook set by you.
     */
    @JsonProperty("secret_token")
    private String secretToken;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("URL parameter can't be empty");
        }
        if (maxConnections < 1 || maxConnections > 100) {
            throw new RuntimeException("Max connections parameter must be in 1..100 range");
        }
        //TODO: implement it
//        if (certificate != null) {
//            if (!certificate.isNew()) {
//                throw new RuntimeException("Certificate parameter must be a new file to upload");
//            }
//        }
        if (secretToken != null && !secretToken.matches("[A-Za-z0-9_-]{1,256}")) {
            throw new RuntimeException("SecretToken parameter must only contains A-Z, a-z, 0-9, _ and -");
        }
    }
}
