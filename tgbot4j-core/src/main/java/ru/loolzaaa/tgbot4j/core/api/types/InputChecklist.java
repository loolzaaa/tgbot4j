package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Describes a checklist to create.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputChecklist {
    /**
     * Title of the checklist; 1-255 characters
     * after entities parsing
     */
    @JsonProperty("title")
    private String title;

    /**
     * Optional. Mode for parsing entities in the title.
     * See <a href="https://core.telegram.org/bots/api#formatting-options">formatting options</a> for more details.
     */
    @JsonProperty("parse_mode")
    private String parseMode;

    /**
     * Optional. List of special entities that appear in the title,
     * which can be specified instead of parse_mode.
     * Currently, only bold, italic, underline, strikethrough,
     * spoiler, and custom_emoji entities are allowed.
     */
    @JsonProperty("title_entities")
    private List<MessageEntity> titleEntities;

    /**
     * List of 1-30 tasks in the checklist
     */
    @JsonProperty("tasks")
    private List<InputChecklistTask> tasks;

    /**
     * Optional. Pass True if other users can add tasks
     * to the checklist
     */
    @JsonProperty("others_can_add_tasks")
    private Boolean othersCanAddTasks;

    /**
     * Optional. Pass True if other users can mark tasks as done
     * or not done in the checklist
     */
    @JsonProperty("others_can_mark_tasks_as_done")
    private Boolean othersCanMarkTasksAsDone;
}
