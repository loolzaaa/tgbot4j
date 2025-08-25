package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a photo to post as a story.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputStoryContentPhoto implements InputStoryContent {
    /**
     * Type of the content, must be photo
     */
    @JsonProperty("type")
    private String type;

    /**
     * The photo to post as a story. The photo must be of the size 1080x1920
     * and must not exceed 10 MB. The photo can't be reused
     * and can only be uploaded as a new file,
     * so you can pass “attach://<file_attach_name>” if the photo
     * was uploaded using multipart/form-data under <file_attach_name>.
     * <a href="https://core.telegram.org/bots/api#sending-files">More information on Sending Files »</a>
     */
    @JsonProperty("photo")
    private InputFile photo;
}
