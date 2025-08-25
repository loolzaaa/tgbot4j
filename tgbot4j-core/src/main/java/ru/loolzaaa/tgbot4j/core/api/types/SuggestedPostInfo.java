package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contains information about a suggested post.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuggestedPostInfo {
    /**
     * State of the suggested post.
     * Currently, it can be one of “pending”,
     * “approved”, “declined”.
     */
    @JsonProperty("state")
    private String state;

    /**
     * Optional. Proposed price of the post.
     * If the field is omitted, then the post is unpaid.
     */
    @JsonProperty("price")
    private SuggestedPostPrice price;

    /**
     * Optional. Proposed send date of the post.
     * If the field is omitted, then the post can be published
     * at any time within 30 days at the sole discretion
     * of the user or administrator who approves it.
     */
    @JsonProperty("send_date")
    private Integer sendDate;
}
