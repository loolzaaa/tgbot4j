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
 * This object describes a message that
 * can be inaccessible to the bot.
 * It can be one of
 * <ul>
 *     <li>{@link Message}</li>
 *     <li>{@link InaccessibleMessage}</li>
 * </ul>
 */

@JsonDeserialize(using = MaybeInaccessibleMessage.MaybeInaccessibleMessageDeserializer.class)
public interface MaybeInaccessibleMessage {
    class MaybeInaccessibleMessageDeserializer extends StdDeserializer<MaybeInaccessibleMessage> {

        public MaybeInaccessibleMessageDeserializer() {
            this(null);
        }

        protected MaybeInaccessibleMessageDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public MaybeInaccessibleMessage deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            ObjectCodec codec = p.getCodec();
            JsonNode node = codec.readTree(p);
            if (!node.has("date")) {
                throw new ApiValidationException("Date parameter must be not null", null);
            }
            if (node.get("date").asInt() == 0) {
                return codec.treeToValue(node, InaccessibleMessage.class);
            } else {
                return codec.treeToValue(node, Message.class);
            }
        }
    }
}
