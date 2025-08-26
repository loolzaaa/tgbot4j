package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Contains the list of gifts received and owned
 * by a user or a chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnedGifts {
    /**
     * The total number of gifts owned by the user or the chat
     */
    @JsonProperty("total_count")
    private Integer totalCount;

    /**
     * The list of gifts
     */
    @JsonProperty("gifts")
    private List<OwnedGift> gifts;

    /**
     * Optional. Offset for the next request.
     * If empty, then there are no more results
     */
    @JsonProperty("next_offset")
    private String nextOffset;
}
