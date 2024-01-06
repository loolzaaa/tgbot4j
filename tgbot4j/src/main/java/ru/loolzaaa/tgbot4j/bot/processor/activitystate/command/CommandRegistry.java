package ru.loolzaaa.tgbot4j.bot.processor.activitystate.command;

import java.util.Collection;

public interface CommandRegistry {

    int COMMAND_NAME_MAX_LENGTH = 32;
    String COMMAND_NAME_PATTERN = "[A-Za-z0-9_]+";
    String COMMAND_START_CHARACTER = "/";
    String COMMAND_USERNAME_PATTERN = "(?i)@\\w+?\\s*$";
    String COMMAND_ARGUMENTS_SEPARATOR_PATTERN = "\\s+";

    void register(Command<?> command);

    void registerAll(Command<?>... commands);

    Command<?> getRegisteredCommand(String commandIdentifier);

    Collection<Command<?>> getRegisteredCommands();
}
