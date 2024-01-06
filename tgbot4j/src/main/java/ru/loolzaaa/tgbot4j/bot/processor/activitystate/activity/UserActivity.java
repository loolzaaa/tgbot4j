package ru.loolzaaa.tgbot4j.bot.processor.activitystate.activity;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.loolzaaa.tgbot4j.bot.processor.activitystate.command.CommandState;

import java.time.LocalDateTime;

/**
 * This class reflects user activity.
 * Instances contain the state of the last active command,
 * as well as the time the user was last active.
 *
 * @see CommandState
 */

@Data
@RequiredArgsConstructor
public class UserActivity {

    private final long userId;

    private CommandState<?> commandState = new CommandState<>(null, null);

    private LocalDateTime lastActivity = LocalDateTime.now();

    public UserActivity(UserActivity userActivity) {
        CommandState<?> originalCommandState = userActivity.getCommandState();

        this.userId = userActivity.getUserId();
        this.commandState = new CommandState<>(originalCommandState.identifier(), originalCommandState.state());
        this.lastActivity = userActivity.getLastActivity();
    }

    public void setCommandState(@NonNull CommandState<?> commandState) {
        this.commandState = commandState;
    }

    public void setLastActivity(@NonNull LocalDateTime lastActivity) {
        this.lastActivity = lastActivity;
    }
}
