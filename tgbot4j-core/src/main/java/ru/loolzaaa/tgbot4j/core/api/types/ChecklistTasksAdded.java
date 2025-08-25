package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Describes a service message about tasks added to a checklist.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistTasksAdded {
    /**
     * Optional. Message containing the checklist
     * to which the tasks were added. Note that the {@link Message}
     * object in this field will not contain the reply_to_message
     * field even if it itself is a reply.
     */
    @JsonProperty("checklist_message")
    private Message checklistMessage;

    /**
     * List of tasks added to the checklist
     */
    @JsonProperty("tasks")
    private List<ChecklistTask> tasks;
}
