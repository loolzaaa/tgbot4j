package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

import java.io.IOException;

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

@JsonDeserialize(using = InlineQueryResult.InlineQueryResultDeserializer.class)
public interface InlineQueryResult {
    class InlineQueryResultDeserializer extends StdDeserializer<InlineQueryResult> {
        public InlineQueryResultDeserializer() {
            this(null);
        }

        protected InlineQueryResultDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public InlineQueryResult deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            ObjectCodec codec = p.getCodec();
            JsonNode node = codec.readTree(p);
            if (node.get("type") == null) {
                throw new ApiValidationException("Type parameter must not be null", null);
            }
            String type = node.get("type").asText();
            switch (type) {
                case "audio" -> {
                    return node.has("audio_url") ?
                            codec.treeToValue(node, InlineQueryResultAudio.class) :
                            codec.treeToValue(node, InlineQueryResultCachedAudio.class);
                }
                case "document" -> {
                    return node.has("document_url") ?
                            codec.treeToValue(node, InlineQueryResultDocument.class) :
                            codec.treeToValue(node, InlineQueryResultCachedDocument.class);
                }
                case "gif" -> {
                    return node.has("gif_url") ?
                            codec.treeToValue(node, InlineQueryResultGif.class) :
                            codec.treeToValue(node, InlineQueryResultCachedGif.class);
                }
                case "video" -> {
                    return node.has("video_url") ?
                            codec.treeToValue(node, InlineQueryResultVideo.class) :
                            codec.treeToValue(node, InlineQueryResultCachedVideo.class);
                }
                case "photo" -> {
                    return node.has("photo_url") ?
                            codec.treeToValue(node, InlineQueryResultPhoto.class) :
                            codec.treeToValue(node, InlineQueryResultCachedPhoto.class);
                }
                case "voice" -> {
                    return node.has("voice_url") ?
                            codec.treeToValue(node, InlineQueryResultVoice.class) :
                            codec.treeToValue(node, InlineQueryResultCachedVoice.class);
                }
                case "mpeg4_gif" -> {
                    return node.has("mpeg4_url") ?
                            codec.treeToValue(node, InlineQueryResultMpeg4Gif.class) :
                            codec.treeToValue(node, InlineQueryResultCachedMpeg4Gif.class);
                }
                case "article" -> {
                    return codec.treeToValue(node, InlineQueryResultArticle.class);
                }
                case "contact" -> {
                    return codec.treeToValue(node, InlineQueryResultContact.class);
                }
                case "game" -> {
                    return codec.treeToValue(node, InlineQueryResultGame.class);
                }
                case "location" -> {
                    return codec.treeToValue(node, InlineQueryResultLocation.class);
                }
                case "venue" -> {
                    return codec.treeToValue(node, InlineQueryResultVenue.class);
                }
                case "sticker" -> {
                    return codec.treeToValue(node, InlineQueryResultCachedSticker.class);
                }
                default -> throw new ApiValidationException("Unknown inline query result type: " + type, null);
            }
        }
    }
}
