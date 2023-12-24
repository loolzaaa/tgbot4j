package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This object represents one result of an inline query.
 * Telegram clients currently support results of the following 20 types:
 * <ul>
 *     <li>{@link InlineQueryResultCachedAudio}</li>
 *     <li>{@link InlineQueryResultCachedDocument}</li>
 *     <li>{@link InlineQueryResultCachedGif}</li>
 *     <li>{@link InlineQueryResultCachedMpeg4Gif}</li>
 *     <li>{@link InlineQueryResultCachedPhoto}</li>
 *     <li>{@link InlineQueryResultCachedSticker}</li>
 *     <li>{@link InlineQueryResultCachedVideo}</li>
 *     <li>{@link InlineQueryResultCachedVoice}</li>
 *     <li>{@link InlineQueryResultArticle}</li>
 *     <li>{@link InlineQueryResultAudio}</li>
 *     <li>{@link InlineQueryResultContact}</li>
 *     <li>{@link InlineQueryResultGame}</li>
 *     <li>{@link InlineQueryResultDocument}</li>
 *     <li>{@link InlineQueryResultGif}</li>
 *     <li>{@link InlineQueryResultLocation}</li>
 *     <li>{@link InlineQueryResultMpeg4Gif}</li>
 *     <li>{@link InlineQueryResultPhoto}</li>
 *     <li>{@link InlineQueryResultVenue}</li>
 *     <li>{@link InlineQueryResultVideo}</li>
 *     <li>{@link InlineQueryResultVoice}</li>
 * </ul>
 *
 * @apiNote All URLs passed in inline query results
 * will be available to end users and therefore
 * must be assumed to be <b>public</b>.
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = InlineQueryResultCachedAudio.class, name = "audio"),
        @JsonSubTypes.Type(value = InlineQueryResultCachedDocument.class, name = "document"),
        @JsonSubTypes.Type(value = InlineQueryResultCachedGif.class, name = "gif"),
        @JsonSubTypes.Type(value = InlineQueryResultCachedMpeg4Gif.class, name = "mpeg4_gif"),
        @JsonSubTypes.Type(value = InlineQueryResultCachedPhoto.class, name = "photo"),
        @JsonSubTypes.Type(value = InlineQueryResultCachedSticker.class, name = "sticker"),
        @JsonSubTypes.Type(value = InlineQueryResultCachedVideo.class, name = "video"),
        @JsonSubTypes.Type(value = InlineQueryResultCachedVoice.class, name = "voice"),
        @JsonSubTypes.Type(value = InlineQueryResultArticle.class, name = "article"),
        @JsonSubTypes.Type(value = InlineQueryResultAudio.class, name = "audio"),
        @JsonSubTypes.Type(value = InlineQueryResultContact.class, name = "contact"),
        @JsonSubTypes.Type(value = InlineQueryResultGame.class, name = "game"),
        @JsonSubTypes.Type(value = InlineQueryResultDocument.class, name = "document"),
        @JsonSubTypes.Type(value = InlineQueryResultGif.class, name = "gif"),
        @JsonSubTypes.Type(value = InlineQueryResultLocation.class, name = "location"),
        @JsonSubTypes.Type(value = InlineQueryResultMpeg4Gif.class, name = "mpeg4_gif"),
        @JsonSubTypes.Type(value = InlineQueryResultPhoto.class, name = "photo"),
        @JsonSubTypes.Type(value = InlineQueryResultVenue.class, name = "venue"),
        @JsonSubTypes.Type(value = InlineQueryResultVideo.class, name = "video"),
        @JsonSubTypes.Type(value = InlineQueryResultVoice.class, name = "voice"),
})
public interface InlineQueryResult {
}
