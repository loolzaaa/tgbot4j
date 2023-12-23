package ru.loolzaaa.tgbot4j.core.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InlineQueryResult {
}
