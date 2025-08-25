package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a video to post as a story.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputStoryContentVideo implements InputStoryContent {
    /**
     * Type of the content, must be video
     */
    @JsonProperty("type")
    private String type;

    /**
     * The video to post as a story. The video must be of the size 720x1280,
     * streamable, encoded with H.265 codec,
     * with key frames added each second in the MPEG4 format,
     * and must not exceed 30 MB. The video can't be reused
     * and can only be uploaded as a new file,
     * so you can pass “attach://<file_attach_name>” if the video
     * was uploaded using multipart/form-data under <file_attach_name>.
     * <a href="https://core.telegram.org/bots/api#sending-files">More information on Sending Files »</a>
     */
    @JsonProperty("video")
    private String video;

    /**
     * Optional. Precise duration of the video in seconds; 0-60
     */
    @JsonProperty("duration")
    private Double duration;

    /**
     * Optional. Timestamp in seconds of the frame
     * that will be used as the static cover for the story.
     * Defaults to 0.0.
     */
    @JsonProperty("cover_frame_timestamp")
    private Double coverFrameTimestamp;

    /**
     * Optional. Pass True if the video has no sound
     */
    @JsonProperty("is_animation")
    private Boolean isAnimation;
}
