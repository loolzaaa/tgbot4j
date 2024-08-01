package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The paid media to send is a video.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputPaidMediaVideo implements InputPaidMedia {
    /**
     * Type of the media, must be video
     */
    @JsonProperty("type")
    private String type;

    /**
     * File to send. Pass a file_id to send a file that exists
     * on the Telegram servers (recommended), pass an HTTP URL
     * for Telegram to get a file from the Internet,
     * or pass “attach://<file_attach_name>” to upload a new one
     * using multipart/form-data under <file_attach_name> name.
     * <a href="https://core.telegram.org/bots/api#sending-files">More information on Sending Files »</a>
     */
    @JsonProperty("media")
    private String media;

    /**
     * Optional. Thumbnail of the file sent; can be ignored
     * if thumbnail generation for the file is supported server-side.
     * The thumbnail should be in JPEG format and less than 200 kB in size.
     * A thumbnail's width and height should not exceed 320.
     * Ignored if the file is not uploaded using multipart/form-data.
     * Thumbnails can't be reused and can be only uploaded as a new file,
     * so you can pass “attach://<file_attach_name>” if the thumbnail
     * was uploaded using multipart/form-data under <file_attach_name>.
     * <a href="https://core.telegram.org/bots/api#sending-files">More information on Sending Files »</a>
     */
    @JsonProperty("thumbnail")
    private InputFile thumbnail;

    /**
     * Optional. Video width
     */
    @JsonProperty("width")
    private Integer width;

    /**
     * Optional. Video height
     */
    @JsonProperty("height")
    private Integer height;

    /**
     * Optional. Video duration in seconds
     */
    @JsonProperty("duration")
    private Integer duration;

    /**
     * Optional. Pass True if the uploaded video
     * is suitable for streaming
     */
    @JsonProperty("supports_streaming")
    private Boolean supportsStreaming;
}
