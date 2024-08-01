package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The paid media is a photo.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaidMediaPhoto implements PaidMedia {
    /**
     * Type of the paid media, always “photo”
     */
    @JsonProperty("type")
    private String type;

    /**
     * The photo
     */
    @JsonProperty("photo")
    private List<PhotoSize> photo;
}
