package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * An animated profile photo in the MPEG4 format.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputProfilePhotoAnimated implements InputProfilePhoto {
    /**
     * Type of the profile photo, must be animated
     */
    @JsonProperty("type")
    private String type;

    /**
     * The animated profile photo. Profile photos can't be reused
     * and can only be uploaded as a new file,
     * so you can pass “attach://<file_attach_name>” if the photo
     * was uploaded using multipart/form-data under <file_attach_name>.
     * <a href="https://core.telegram.org/bots/api#sending-files">More information on Sending Files »</a>
     */
    @JsonProperty("photo")
    private InputFile photo;

    /**
     * Optional. Timestamp in seconds of the frame
     * that will be used as the static profile photo.
     * Defaults to 0.0.
     */
    @JsonProperty("main_frame_timestamp")
    private Double mainFrameTimestamp;
}
