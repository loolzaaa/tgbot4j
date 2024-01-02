package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes the options used for link preview generation.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkPreviewOptions {
    /**
     * Optional. True, if the link preview is disabled
     */
    @JsonProperty("is_disabled")
    private Boolean isDisabled;

    /**
     * Optional. URL to use for the link preview.
     * If empty, then the first URL found
     * in the message text will be used
     */
    @JsonProperty("url")
    private String url;

    /**
     * Optional. True, if the media in the link preview
     * is suppposed to be shrunk; ignored if the URL
     * isn't explicitly specified or media size change
     * isn't supported for the preview
     */
    @JsonProperty("prefer_small_media")
    private Boolean preferSmallMedia;

    /**
     * Optional. True, if the media in the link preview
     * is suppposed to be enlarged; ignored if the URL
     * isn't explicitly specified or media size change
     * isn't supported for the preview
     */
    @JsonProperty("prefer_large_media")
    private Boolean preferLargeMedia;

    /**
     * Optional. True, if the link preview must be shown
     * above the message text; otherwise, the link preview
     * will be shown below the message text
     */
    @JsonProperty("show_above_text")
    private Boolean showAboveText;
}
