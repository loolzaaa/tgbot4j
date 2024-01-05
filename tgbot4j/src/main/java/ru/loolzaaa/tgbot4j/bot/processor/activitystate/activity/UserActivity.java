package ru.loolzaaa.tgbot4j.bot.processor.activitystate.activity;

import lombok.*;
import ru.loolzaaa.tgbot4j.bot.processor.activitystate.command.CommandState;

import java.time.LocalDateTime;

/**
 * This class reflects user activity.
 * Instances contain the state of the last active command,
 * as well as the time the user was last active.
 *
 * @see CommandState
 */

@EqualsAndHashCode
@ToString
@Getter
@Setter(onParam_ = @NonNull)
public class UserActivity {
    private CommandState<?> commandState = new CommandState<>(null, null);
    private LocalDateTime lastActivity = LocalDateTime.now();
}
