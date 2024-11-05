package ru.loolzaaa.tgbot4j.bot.processor.activitystate.command;

import java.util.Collection;

/**
 * Interface for bot command registry implementation.
 * <p>
 * By default, registry is read only. That means,
 * that after registry a command,
 * you cannot remove it from registry.
 */

public interface CommandRegistry {
    /**
     * Max characters length of command.
     */
    int COMMAND_NAME_MAX_LENGTH = 32;

    /**
     * Regular expression pattern for command name.
     */
    String COMMAND_NAME_PATTERN = "[A-Za-z0-9_]+";

    /**
     * Character that defines start of command.
     */
    String COMMAND_START_CHARACTER = "/";

    /**
     * Regular expression pattern for command with user mention.
     */
    String COMMAND_USERNAME_PATTERN = "(?i)@\\w+?\\s*$";

    /**
     * Regualr expression separator for commands.
     */
    String COMMAND_ARGUMENTS_SEPARATOR_PATTERN = "\\s+";

    /**
     * Register new command in registry.
     *
     * @param command command to register
     */
    void register(Command<?> command);

    /**
     * Register all commands from arguments in registry.
     *
     * @param commands commands to register
     */
    void registerAll(Command<?>... commands);

    /**
     * Get registered command from registry by command identifier.
     *
     * @param commandIdentifier identifier of receiving command
     * @return registered command, or null if unknown
     * @implSpec If unknown command identifier, must return null.
     */
    Command<?> getRegisteredCommand(String commandIdentifier);

    /**
     * Get all registered commands from registry.
     *
     * @return collection of registered commands
     */
    Collection<Command<?>> getRegisteredCommands();
}
