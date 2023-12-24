package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object represent a user's profile pictures.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfilePhotos {
    /**
     * Total number of profile pictures the target user has
     */
    @JsonProperty("total_count")
    private Integer totalCount;

    /**
     * Requested profile pictures (in up to 4 sizes each)
     */
    @JsonProperty("photos")
    private List<List<PhotoSize>> photos;
}
