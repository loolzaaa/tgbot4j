package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object represents the audios displayed on a user's profile.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileAudios {
    /**
     * Total number of profile audios for the target user
     */
    @JsonProperty("total_count")
    private Integer totalCount;

    /**
     * Requested profile audios
     */
    @JsonProperty("audios")
    private List<Audio> audios;
}
