package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Describes a task in a checklist.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistTask {
    /**
     * Unique identifier of the task
     */
    @JsonProperty("id")
    private Integer id;

    /**
     * Text of the task
     */
    @JsonProperty("text")
    private String text;

    /**
     * Optional. Special entities that appear in the task text
     */
    @JsonProperty("text_entities")
    private List<MessageEntity> textEntities;

    /**
     * Optional. User that completed the task;
     * omitted if the task wasn't completed
     */
    @JsonProperty("completed_by_user")
    private User completedByUser;

    /**
     * Optional. Point in time (Unix timestamp) when the task
     * was completed; 0 if the task wasn't completed
     */
    @JsonProperty("completion_date")
    private Integer completionDate;
}
