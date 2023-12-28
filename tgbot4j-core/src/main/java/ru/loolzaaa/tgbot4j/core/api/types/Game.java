package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.methods.SetGameScore;
import ru.loolzaaa.tgbot4j.core.api.methods.EditMessageText;

import java.util.List;

/**
 * This object represents a game. Use BotFather to create and edit games,
 * their short names will act as unique identifiers.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    /**
     * Title of the game
     */
    @JsonProperty("title")
    private String title;

    /**
     * Description of the game
     */
    @JsonProperty("description")
    private String description;

    /**
     * Photo that will be displayed in the game message in chats.
     */
    @JsonProperty("photo")
    private List<PhotoSize> photo;

    /**
     * Optional. Brief description of the game or high scores included in the game message.
     * Can be automatically edited to include current high scores for the game
     * when the bot calls {@link SetGameScore}, or manually edited using {@link EditMessageText}.
     * 0-4096 characters.
     */
    @JsonProperty("text")
    private String text;

    /**
     * Optional. Special entities that appear in text, such as usernames, URLs, bot commands, etc.
     */
    @JsonProperty("text_entities")
    private List<MessageEntity> textEntities;

    /**
     * Optional. Animation that will be displayed in the game message in chats. Upload via BotFather
     */
    @JsonProperty("animation")
    private Animation animation;
}
