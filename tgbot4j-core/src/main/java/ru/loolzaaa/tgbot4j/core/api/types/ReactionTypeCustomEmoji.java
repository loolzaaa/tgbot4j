package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The reaction is based on a custom emoji.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactionTypeCustomEmoji {
    @JsonProperty("type")
    private String type;
    @JsonProperty("custom_emoji_id")
    private String customEmojiId;
}
