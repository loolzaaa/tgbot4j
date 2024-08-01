package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Describes the paid media added to a message.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaidMediaInfo {
    /**
     * The number of Telegram Stars that must be paid
     * to buy access to the media
     */
    @JsonProperty("star_count")
    private Integer starCount;

    /**
     * Information about the paid media
     */
    @JsonProperty("paid_media")
    private List<PaidMedia> paidMedia;
}
