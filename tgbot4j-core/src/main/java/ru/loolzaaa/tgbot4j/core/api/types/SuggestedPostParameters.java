package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contains parameters of a post that
 * is being suggested by the bot.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuggestedPostParameters {
    /**
     * Optional. Proposed price for the post.
     * If the field is omitted, then the post is unpaid.
     */
    @JsonProperty("price")
    private SuggestedPostPrice price;

    /**
     * Optional. Proposed send date of the post.
     * If specified, then the date must be between 300 second
     * and 2678400 seconds (30 days) in the future.
     * If the field is omitted, then the post can be published
     * at any time within 30 days at the sole discretion
     * of the user who approves it.
     */
    @JsonProperty("send_date")
    private Integer sendDate;
}
