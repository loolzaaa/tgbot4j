package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Describes the current status of a webhook.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebhookInfo {
    /**
     * Webhook URL, may be empty if webhook is not set up
     */
    @JsonProperty("url")
    private String url;

    /**
     * True, if a custom certificate was provided
     * for webhook certificate checks
     */
    @JsonProperty("has_custom_certificate")
    private Boolean hasCustomCertificate;

    /**
     * Number of updates awaiting delivery
     */
    @JsonProperty("pending_update_count")
    private Integer pendingUpdateCount;

    /**
     * Optional. Currently used webhook IP address
     */
    @JsonProperty("ip_address")
    private String ipAddress;

    /**
     * Optional. Unix time for the most recent error
     * that happened when trying to deliver an update via webhook
     */
    @JsonProperty("last_error_date")
    private Integer lastErrorDate;

    /**
     * Optional. Error message in human-readable format
     * for the most recent error that happened
     * when trying to deliver an update via webhook
     */
    @JsonProperty("last_error_message")
    private String lastErrorMessage;

    /**
     * Optional. Unix time of the most recent error
     * that happened when trying to synchronize available updates
     * with Telegram datacenters
     */
    @JsonProperty("last_synchronization_error_date")
    private Integer lastSynchronizationErrorDate;

    /**
     * Optional. The maximum allowed number of simultaneous
     * HTTPS connections to the webhook for update delivery
     */
    @JsonProperty("max_connections")
    private Integer maxConnections;

    /**
     * Optional. A list of update types the bot is subscribed to.
     * Defaults to all update types except chat_member
     */
    @JsonProperty("allowed_updates")
    private List<String> allowedUpdates;
}
