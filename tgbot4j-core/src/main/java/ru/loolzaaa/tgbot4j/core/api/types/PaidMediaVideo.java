package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The paid media is a video.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaidMediaVideo implements PaidMedia {
    /**
     * Type of the paid media, always “video”
     */
    @JsonProperty("type")
    private String type;

    /**
     * The video
     */
    @JsonProperty("video")
    private Video video;
}
