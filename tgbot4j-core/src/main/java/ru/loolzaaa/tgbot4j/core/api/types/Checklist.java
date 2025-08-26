package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Describes a checklist.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Checklist {
    /**
     * Title of the checklist
     */
    @JsonProperty("title")
    private String title;

    /**
     * Optional. Special entities that appear in the checklist title
     */
    @JsonProperty("title_entities")
    private List<MessageEntity> titleEntities;

    /**
     * List of tasks in the checklist
     */
    @JsonProperty("tasks")
    private List<ChecklistTask> tasks;

    /**
     * Optional. True, if users other than the creator
     * of the list can add tasks to the list
     */
    @JsonProperty("others_can_add_tasks")
    private Boolean othersCanAddTasks;

    /**
     * Optional. True, if users other than the creator
     * of the list can mark tasks as done or not done
     */
    @JsonProperty("others_can_mark_tasks_as_done")
    private Boolean othersCanMarkTasksAsDone;
}
