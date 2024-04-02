package ru.loolzaaa.tgbot4j.bot.processor.activitystate;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.loolzaaa.tgbot4j.bot.processor.activitystate.activity.DefaultUserActivityHandler;
import ru.loolzaaa.tgbot4j.bot.processor.activitystate.activity.UserActivity;
import ru.loolzaaa.tgbot4j.bot.processor.activitystate.activity.UserActivityHandler;
import ru.loolzaaa.tgbot4j.bot.processor.activitystate.command.Command;
import ru.loolzaaa.tgbot4j.bot.processor.activitystate.command.CommandRegistry;
import ru.loolzaaa.tgbot4j.bot.processor.activitystate.command.CommandState;
import ru.loolzaaa.tgbot4j.bot.processor.activitystate.command.DefaultCommandRegistry;
import ru.loolzaaa.tgbot4j.core.api.types.CallbackQuery;
import ru.loolzaaa.tgbot4j.core.api.types.Message;
import ru.loolzaaa.tgbot4j.core.api.types.MessageEntity;
import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.bot.processor.UpdateProcessor;
import ru.loolzaaa.tgbot4j.core.bot.processor.UpdateProcessorChain;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

import static ru.loolzaaa.tgbot4j.bot.processor.activitystate.command.CommandRegistry.*;

/**
 * Implementation of update processor for work
 * with command state and user activity.
 *
 * @apiNote Works only if the button that originated
 * the query was attached to a message sent by the bot
 */

public class CommandStateUpdateProcessor implements UpdateProcessor {

    private static final Logger log = LoggerFactory.getLogger(CommandStateUpdateProcessor.class);

    @Getter
    private final CommandRegistry commandRegistry;

    private final UserActivityHandler userActivityHandler;

    private final CommandExceptionHandler commandExceptionHandler;

    @Setter
    private int userInactivityMaxTime = 20;

    /**
     * Constructor creates new command state processor.
     * <p>
     * If some argument is null, default implementation is used.
     *
     * @param commandRegistry         registry for command storing
     * @param userActivityHandler     handler for storing user activity
     * @param commandExceptionHandler handler for command exceptions
     */
    public CommandStateUpdateProcessor(CommandRegistry commandRegistry,
                                       UserActivityHandler userActivityHandler,
                                       CommandExceptionHandler commandExceptionHandler) {
        this.commandRegistry = Objects.requireNonNullElseGet(commandRegistry, DefaultCommandRegistry::new);
        this.userActivityHandler = Objects.requireNonNullElseGet(userActivityHandler, DefaultUserActivityHandler::new);
        this.commandExceptionHandler = Objects.requireNonNullElse(commandExceptionHandler, (e, update, methodSender) -> {
        });
    }

    /**
     * Incoming message handler for command state processing.
     * <p>
     * Process only one of {@link Message} or {@link CallbackQuery}.
     * <p>
     * Command can be start only from incoming {@link Message} object.
     * <p>
     * Command always should be continued from {@link CallbackQuery} object.
     * From {@link Message} object command would be continued
     * only if message not start with command pattern,
     * else it starts new command process.
     *
     * @param update       fresh update from API server
     * @param methodSender sender for API server communication
     * @param chain        update processors chain
     */
    @Override
    public void process(Update update, MethodSender methodSender, UpdateProcessorChain chain) {
        if (log.isTraceEnabled()) {
            log.trace("{} incoming update: {}", CommandStateUpdateProcessor.class.getSimpleName(), update);
        }
        /*
         Only one (Message/CallbackQuery) can be present in any given update
         https://core.telegram.org/bots/api#update
        */
        if (update.getMessage() != null || update.getCallbackQuery() != null) {
            try {
                Message message = update.getMessage();
                CallbackQuery callbackQuery = update.getCallbackQuery();
                if (message != null) {
                    if (isCommandMessage(message)) {
                        startCommandProcess(methodSender, message);
                    } else {
                    /*
                     It is currently unclear whether this is a continuation
                     of the command or just a message
                    */
                        continueCommandProcessWithMessage(methodSender, message);
                    }
                    chain.doProcess(update, methodSender);
                    return;
                } else if (callbackQuery.getMessage() != null) {
                    continueCommandProcessWithCallbackQuery(methodSender, callbackQuery);
                    chain.doProcess(update, methodSender);
                    return;
                }
            } catch (ProcessCommandException e) {
                if (log.isDebugEnabled()) {
                    log.debug("Command execution exception: ", e);
                }
                // The user has interacted with the bot via commands previously
                if (e.getUserId() != null) {
                    log.info("Command '{}' execution for user {} exception: {}",
                            e.getCommandIdentifier(), e.getUserId(), e.getLocalizedMessage());
                    commandExceptionHandler.handle(e, update, methodSender);
                }
            }
        }
        chain.doProcess(update, methodSender);
    }

    /**
     * Start new command processing from {@link Message} object.
     * <p>
     * Creates and saves new user activity,
     * execute command with null command state.
     *
     * @param methodSender sender for API server communication
     * @param message      incoming message
     */
    private void startCommandProcess(MethodSender methodSender, Message message) {
        long userId = message.getFrom().getId();
        UserActivity userActivity = new UserActivity(userId);
        userActivityHandler.saveUserActivity(userId, userActivity);
        CommandState<?> resultState = executeCommand(message, methodSender, userActivity.getCommandState());
        validateResultState(resultState, userActivity);
        log.info("Complete new command for user {} with result state: {}", userId, resultState);
    }

    /**
     * Continue command processing from {@link Message} object.
     * <p>
     * Loads saved user activity, validate it and
     * execute command with saved command state.
     * After all, validate new command state.
     *
     * @param methodSender sender for API server communication
     * @param message      incoming message
     */
    private void continueCommandProcessWithMessage(MethodSender methodSender, Message message) {
        long userId = message.getFrom().getId();
        UserActivity userActivity = userActivityHandler.loadUserActivity(userId);
        validateUserActivity(userActivity);
        CommandState<?> resultState = executeCommand(message, methodSender, userActivity.getCommandState());
        validateResultState(resultState, userActivity);
        log.info("Continued command complete for user {} with result state: {}", userId, resultState);
    }

    /**
     * Continue command processing from {@link Message} object.
     * <p>
     * Loads saved user activity, validate it and
     * execute command with saved command state.
     * After all, validate new command state.
     *
     * @param methodSender  sender for API server communication
     * @param callbackQuery incoming callback query
     */
    private void continueCommandProcessWithCallbackQuery(MethodSender methodSender, CallbackQuery callbackQuery) {
        long userId = callbackQuery.getFrom().getId();
        UserActivity userActivity = userActivityHandler.loadUserActivity(userId);
        validateUserActivity(userActivity);
        CommandState<?> resultState = executeCommand(callbackQuery, methodSender, userActivity.getCommandState());
        validateResultState(resultState, userActivity);
        log.info("Continued command complete for user {} with result state: {}", userId, resultState);
    }

    /**
     * Executing command from {@link Message} object.
     * <p>
     * Text of message must not be null and can,
     * but not must starts with command start character.
     * <p>
     * Command start character means start new command,
     * else continue command with simple message.
     * <p>
     * If command not contains in command registry,
     * method return completed null command.
     *
     * @param message      incoming message
     * @param methodSender sender for API server communication
     * @param commandState current command state
     * @return new command state
     */
    private CommandState<?> executeCommand(Message message, MethodSender methodSender, CommandState<?> commandState) {
        if (message.getText() == null || message.getText().isEmpty()) {
            throw new ProcessCommandException("The message does not contain text to continue the command",
                    message.getFrom().getId(), commandState.identifier());
        }
        String messageText = message.getText();
        if (messageText.startsWith(COMMAND_START_CHARACTER)) {
            // Start new command
            String commandMessage = messageText.substring(1);
            String[] commandSplit = commandMessage.split(COMMAND_ARGUMENTS_SEPARATOR_PATTERN);

            // Remove username from command if exists
            String commandIdentifier = commandSplit[0].replaceAll(COMMAND_USERNAME_PATTERN, "").trim();

            Command<?> command = commandRegistry.getRegisteredCommand(commandIdentifier);
            if (command != null) {
                if (log.isDebugEnabled()) {
                    log.debug("Start new command '{}' for user {}", commandIdentifier, message.getFrom().getId());
                }
                String[] parameters = Arrays.copyOfRange(commandSplit, 1, commandSplit.length);
                return command.processCommand(methodSender, message, parameters, commandState);
            }
        } else {
            // Continue some command with simple message
            String commandIdentifier = commandState.identifier();


            if (commandIdentifier != null) {
                Command<?> command = commandRegistry.getRegisteredCommand(commandIdentifier);
                if (command != null) {
                    if (log.isDebugEnabled()) {
                        log.debug("Continue command '{}' for user {}", commandIdentifier, message.getFrom().getId());
                    }
                    return command.processCommand(methodSender, message, new String[0], commandState);
                }
            }
        }
        return new CommandState<>(null, null);
    }

    /**
     * Executing command from {@link CallbackQuery} object.
     * <p>
     * Text of message of callback must not be null.
     * <p>
     * If command not contains in command registry,
     * method return completed null command.
     *
     * @param callbackQuery incoming callback query
     * @param methodSender  sender for API server communication
     * @param commandState  current command state
     * @return new command state
     */
    private CommandState<?> executeCommand(CallbackQuery callbackQuery, MethodSender methodSender, CommandState<?> commandState) {
        String callbackQueryId = callbackQuery.getId();
        if (!(callbackQuery.getMessage() instanceof Message message)) {
            throw new ProcessCommandException("Try to continue command with InaccessibleMessage: " + callbackQuery.getMessage(),
                    callbackQuery.getFrom().getId(), commandState.identifier());
        }
        String commandMessage = callbackQuery.getData();
        String[] parameters = new String[]{callbackQueryId};
        if (commandMessage != null) {
            String[] splitCommandParams = commandMessage.split(COMMAND_ARGUMENTS_SEPARATOR_PATTERN);
            parameters = Arrays.copyOf(parameters, 1 + splitCommandParams.length);
            System.arraycopy(splitCommandParams, 0, parameters, 1, splitCommandParams.length);
        }

        String commandIdentifier = commandState.identifier();

        if (commandIdentifier != null) {
            Command<?> command = commandRegistry.getRegisteredCommand(commandIdentifier);
            if (command != null) {
                if (log.isDebugEnabled()) {
                    log.debug("Continue command '{}' for user {}", commandIdentifier, callbackQuery.getFrom().getId());
                }
                return command.processCommand(methodSender, message, parameters, commandState);
            }
        }
        return new CommandState<>(null, null);
    }

    /**
     * User activity validation.
     * <p>
     * It must not be null and last activity date/time
     * must not exceed limit.
     * <p>
     * If success validation, update last activity value.
     *
     * @param userActivity validating user activity
     */
    private void validateUserActivity(UserActivity userActivity) {
        if (userActivity == null) {
            throw new ProcessCommandException("User activity is null, cannot validate", null, null);
        }
        LocalDateTime now = LocalDateTime.now();
        if (now.minusMinutes(userInactivityMaxTime).isAfter(userActivity.getLastActivity())) {
            userActivityHandler.removeUserActivity(userActivity.getUserId());
            throw new ProcessCommandException("The user has exceeded the maximum inactivity time",
                    userActivity.getUserId(), userActivity.getCommandState().identifier());
        }
        if (log.isDebugEnabled()) {
            log.debug("Update user {} last activity. Was: {}", userActivity.getUserId(), userActivity.getLastActivity());
        }
        userActivity.setLastActivity(now);
    }

    /**
     * Command state validation.
     * <p>
     * It must not be null. If validation fails,
     * remove user activity from handler.
     * <p>
     * If state actually is null, that means finish
     * command processing, allow remove user activity.
     * <p>
     * If state actually some value, need to continue
     * command processing later, so save user activity.
     *
     * @param commandState validating command state
     * @param userActivity existing user activity
     */
    private void validateResultState(CommandState<?> commandState, UserActivity userActivity) {
        if (commandState != null) {
            if (commandState.state() != null) {
                userActivity.setCommandState(commandState);
                userActivityHandler.saveUserActivity(userActivity.getUserId(), userActivity);
            } else {
                userActivityHandler.removeUserActivity(userActivity.getUserId());
            }
        } else {
            userActivityHandler.removeUserActivity(userActivity.getUserId());
            throw new ProcessCommandException("Command state cannot be null after command execution",
                    userActivity.getUserId(), userActivity.getCommandState().identifier());
        }
    }

    private boolean isCommandMessage(Message message) {
        if (message.getText() != null && !message.getText().isEmpty() && message.getEntities() != null) {
            for (MessageEntity entity : message.getEntities()) {
                if (entity != null && entity.getOffset() == 0 && "bot_command".equals(entity.getType())) {
                    return true;
                }
            }
        }
        return false;
    }
}
