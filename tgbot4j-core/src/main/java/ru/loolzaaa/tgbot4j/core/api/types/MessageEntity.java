package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.methods.GetCustomEmojiStickers;

/**
 * This object represents one special entity in a text message.
 * For example, hashtags, usernames, URLs, etc.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {
    /**
     * Type of the entity. Currently, can be “mention” ({@code @username}),
     * “hashtag” ({@code #hashtag} or {@code #hashtag@chatusername}),
     * “cashtag” ({@code $USD} or {@code $USD@chatusername}), “bot_command” ({@code /start@jobs_bot}),
     * “url” ({@code https://telegram.org}), “email” ({@code do-not-reply@telegram.org}),
     * “phone_number” ({@code +1-212-555-0123}), “bold” (<b>bold text</b>), “italic” (<i>italic text</i>),
     * “underline” (underlined text), “strikethrough” (strikethrough text),
     * “spoiler” (spoiler message), “blockquote” (block quotation),
     * “expandable_blockquote” (collapsed-by-default block quotation),
     * “code” (monowidth string), “pre” (monowidth block),
     * “text_link” (for clickable text URLs), “text_mention” (for users <a href="https://telegram.org/blog/edit#new-mentions">without usernames</a>),
     * “custom_emoji” (for inline custom emoji stickers),
     * or “date_time” (for formatted date and time)
     */
    @JsonProperty("type")
    private String type;

    /**
     * Offset in <a href="https://core.telegram.org/api/entities#entity-length">UTF-16 code units</a> to the start of the entity
     */
    @JsonProperty("offset")
    private Integer offset;

    /**
     * Length of the entity in <a href="https://core.telegram.org/api/entities#entity-length">UTF-16 code units</a>
     */
    @JsonProperty("length")
    private Integer length;

    /**
     * Optional. For “text_link” only,
     * URL that will be opened after user taps on the text
     */
    @JsonProperty("url")
    private String url;

    /**
     * Optional. For “text_mention” only, the mentioned user
     */
    @JsonProperty("user")
    private User user;

    /**
     * Optional. For “pre” only,
     * the programming language of the entity text
     */
    @JsonProperty("language")
    private String language;

    /**
     * Optional. For “custom_emoji” only, unique identifier of the custom emoji.
     * Use {@link GetCustomEmojiStickers}
     * to get full information about the sticker
     */
    @JsonProperty("custom_emoji_id")
    private String customEmojiId;

    /**
     * Optional. For “date_time” only, the Unix time
     * associated with the entity
     */
    @JsonProperty("unix_time")
    private Integer unixTime;

    /**
     * Optional. For “date_time” only, the string
     * that defines the formatting of the date and time.
     * See <a href="https://core.telegram.org/bots/api#date-time-entity-formatting">date-time entity formatting</a> for more details.
     */
    @JsonProperty("date_time_format")
    private String dateTimeFormat;
}
