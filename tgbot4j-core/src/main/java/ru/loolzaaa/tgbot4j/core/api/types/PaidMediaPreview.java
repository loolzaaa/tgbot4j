package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The paid media isn't available before the payment.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaidMediaPreview implements PaidMedia {
    /**
     * Type of the paid media, always “preview”
     */
    @JsonProperty("type")
    private String type;

    /**
     * Optional. Media width as defined by the sender
     */
    @JsonProperty("width")
    private Integer width;

    /**
     * Optional. Media height as defined by the sender
     */
    @JsonProperty("height")
    private Integer height;

    /**
     * Optional. Duration of the media in seconds
     * as defined by the sender
     */
    @JsonProperty("duration")
    private Integer duration;
}
