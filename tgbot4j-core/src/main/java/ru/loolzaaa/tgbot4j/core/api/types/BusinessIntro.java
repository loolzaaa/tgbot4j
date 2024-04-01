package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * No description
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessIntro {
    /**
     * Optional. Title text of the business intro
     */
    @JsonProperty("title")
    private String title;

    /**
     * Optional. Message text of the business intro
     */
    @JsonProperty("message")
    private String message;

    /**
     * Optional. Sticker of the business intro
     */
    @JsonProperty("sticker")
    private Sticker sticker;
}
