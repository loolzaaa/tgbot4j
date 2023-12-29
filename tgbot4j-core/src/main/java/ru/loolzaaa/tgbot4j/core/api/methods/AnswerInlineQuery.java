package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.InlineQueryResult;
import ru.loolzaaa.tgbot4j.core.api.types.InlineQueryResultsButton;

import java.util.List;

/**
 * Use this method to send answers to an inline query.
 * On success, True is returned.
 * <p>
 * No more than 50 results per query are allowed.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerInlineQuery implements TelegramMethod<Boolean> {
    /**
     * Unique identifier for the answered query
     */
    @JsonProperty("inline_query_id")
    private String inlineQueryId;

    /**
     * A JSON-serialized array of results for the inline query
     */
    @JsonProperty("results")
    private List<InlineQueryResult> results;

    /**
     * The maximum amount of time in seconds that the result
     * of the inline query may be cached on the server.
     * Defaults to 300.
     */
    @JsonProperty("cache_time")
    private Integer cacheTime;

    /**
     * Pass True if results may be cached on the server side
     * only for the user that sent the query.
     * By default, results may be returned to any user
     * who sends the same query.
     */
    @JsonProperty("is_personal")
    private Boolean isPersonal;

    /**
     * Pass the offset that a client should send in the next query
     * with the same text to receive more results. Pass an empty string
     * if there are no more results or if you don't support pagination.
     * Offset length can't exceed 64 bytes.
     */
    @JsonProperty("next_offset")
    private String nextOffset;

    /**
     * A JSON-serialized object describing a button
     * to be shown above inline query results
     */
    @JsonProperty("button")
    private InlineQueryResultsButton button;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}
