package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The paid media to send is a photo.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputPaidMediaPhoto implements InputPaidMedia {
    /**
     * Type of the media, must be photo
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
}
