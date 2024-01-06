package ru.loolzaaa.tgbot4j.bot.processor.activitystate.command;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultCommandRegistry implements CommandRegistry {

    private static final Logger log = LoggerFactory.getLogger(DefaultCommandRegistry.class);

    private final Map<String, Command<?>> commandMap = new ConcurrentHashMap<>();

    @Override
    public void register(@NonNull Command<?> command) {
        if (command.getIdentifier() == null || command.getIdentifier().isEmpty()) {
            throw new IllegalArgumentException("Command identifier must not be null or empty: " + command.getIdentifier());
        }
        if (!command.getIdentifier().matches(COMMAND_NAME_PATTERN)) {
            throw new IllegalArgumentException("Command identifier can use Latin letters, numbers and underscores: " + command.getIdentifier());
        }
        if (command.getIdentifier().length() > COMMAND_NAME_MAX_LENGTH) {
            throw new IllegalArgumentException("Command identifier must contain up to 31 characters: " + command.getIdentifier());
        }
        if (commandMap.containsKey(command.getIdentifier())) {
            log.warn("Command with identifier '{}' is already registered. Only the last one will be saved", command.getIdentifier());
        }
        commandMap.put(command.getIdentifier(), command);
    }

    @Override
    public void registerAll(Command<?>... commands) {
        for (Command<?> command : commands) {
            register(command);
        }
    }

    @Override
    public Command<?> getRegisteredCommand(String commandIdentifier) {
        return commandMap.get(commandIdentifier);
    }

    @Override
    public Collection<Command<?>> getRegisteredCommands() {
        return commandMap.values();
    }
}
