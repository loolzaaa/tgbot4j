package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A static profile photo in the .JPG format.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputProfilePhotoStatic implements InputProfilePhoto {
    /**
     * Type of the profile photo, must be static
     */
    @JsonProperty("type")
    private String type;

    /**
     * The static profile photo. Profile photos can't be reused
     * and can only be uploaded as a new file,
     * so you can pass “attach://<file_attach_name>” if the photo
     * was uploaded using multipart/form-data under <file_attach_name>.
     * <a href="https://core.telegram.org/bots/api#sending-files">More information on Sending Files »</a>
     */
    @JsonProperty("photo")
    private InputFile photo;
}
