package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object contains information about a paid media purchase.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaidMediaPurchased {
    /**
     * User who purchased the media
     */
    @JsonProperty("from")
    private User from;

    /**
     * Bot-specified paid media payload
     */
    @JsonProperty("paid_media_payload")
    private String paidMediaPayload;
}
