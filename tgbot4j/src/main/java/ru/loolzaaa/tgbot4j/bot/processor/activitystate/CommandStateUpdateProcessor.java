package ru.loolzaaa.tgbot4j.bot.processor.activitystate;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.loolzaaa.tgbot4j.bot.processor.activitystate.activity.UserActivity;
import ru.loolzaaa.tgbot4j.bot.processor.activitystate.activity.UserActivityHandler;
import ru.loolzaaa.tgbot4j.bot.processor.activitystate.command.Command;
import ru.loolzaaa.tgbot4j.bot.processor.activitystate.command.CommandState;
import ru.loolzaaa.tgbot4j.core.api.types.CallbackQuery;
import ru.loolzaaa.tgbot4j.core.api.types.Message;
import ru.loolzaaa.tgbot4j.core.api.types.MessageEntity;
import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.bot.processor.UpdateProcessor;
import ru.loolzaaa.tgbot4j.core.bot.processor.UpdateProcessorChain;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CommandStateUpdateProcessor implements UpdateProcessor {

    private final Map<String, Command<?>> commandRegistry = new HashMap<>();

    private final UserActivityHandler userActivityHandler;

    @Setter
    private int userInactivityMaxTime = 20;

    @Override
    public void process(Update update, MethodSender methodSender, UpdateProcessorChain chain) {
        /*
         Only one (Message/CallbackQuery) can be present in any given update
         https://core.telegram.org/bots/api#update
        */
        if (update.getMessage() != null || update.getCallbackQuery() != null) {
            Message message = update.getMessage();
            CallbackQuery callbackQuery = update.getCallbackQuery();
            if (message != null) {
                if (isMessageCommand(message)) {
                    if (userActivityHandler == null) {
                        executeStatelessCommand(update, methodSender, message);
                    } else {
                        executeStatefulCommand(update, methodSender, message);
                    }
                    chain.doProcess(update, methodSender);
                    return;
                } else if (userActivityHandler != null) {
                    continueStatefulCommandWithMessage(update, methodSender, message);
                    chain.doProcess(update, methodSender);
                    return;
                }
            } else if (callbackQuery.getMessage() != null && userActivityHandler != null) {
                continueStatefulCommandWithCallbackQuery(update, methodSender, callbackQuery);
                chain.doProcess(update, methodSender);
                return;
            }
        }
        // non-command update
        chain.doProcess(update, methodSender);
    }

    public void register(Command<?> command) {
        commandRegistry.put(command.getIdentifier(), command);
    }

    private void executeStatelessCommand(Update update, MethodSender methodSender, Message message) {
        CommandState<?> resultState = executeCommand(message, methodSender, null);
        if (!commandRegistry.executeCommand(this, message)) {
            processInvalidCommandUpdate(update);
        }
    }

    private void executeStatefulCommand(Update update, MethodSender methodSender, Message message) {
        long userId = message.getFrom().getId();
        UserActivity userActivity = new UserActivity();
        userActivityHandler.saveUserActivity(userId, userActivity);
        CommandState<?> resultState = executeCommand(message, methodSender, userActivity.getCommandState());
        if (!checkCommandState(resultState, userId, userActivity)) {
            processInvalidCommandUpdate(update);
        }
    }

    private void continueStatefulCommandWithMessage(Update update, MethodSender methodSender, Message message) {
        long userId = message.getFrom().getId();
        UserActivity userActivity = userActivityHandler.loadUserActivity(userId);
        if (userActivity != null && validateUserActivity(userActivity)) {
            CommandState<?> resultState = executeCommand(message, methodSender, userActivity.getCommandState());
            if (!checkCommandState(resultState, userId, userActivity)) {
                processInvalidCommandUpdate(update);
            }
        } else {
            processInvalidCommandUpdate(update);
        }
    }

    private void continueStatefulCommandWithCallbackQuery(Update update, MethodSender methodSender, CallbackQuery callbackQuery) {
        long userId = callbackQuery.getFrom().getId();
        UserActivity userActivity = userActivityHandler.loadUserActivity(userId);
        if (userActivity != null && validateUserActivity(userActivity)) {
            CommandState<?> resultState = executeCommand(callbackQuery, methodSender, userActivity.getCommandState());
            if (!checkCommandState(resultState, userId, userActivity)) {
                processInvalidCommandUpdate(update);
            }
        } else {
            processInvalidCommandUpdate(update);
        }
    }

    private CommandState<?> executeCommand(Message message, MethodSender methodSender, CommandState<?> commandState) {
        if (message.getText() != null && !message.getText().isEmpty()) {
            String text = message.getText();
            if (text.startsWith("/")) {
                // Start new command
                String commandMessage = text.substring(1);
                String[] commandSplit = commandMessage.split("\\s+");

                String command = removeUsernameFromCommandIfNeeded(commandSplit[0]);

                if (commandRegistry.containsKey(command)) {
                    String[] parameters = Arrays.copyOfRange(commandSplit, 1, commandSplit.length);
                    return commandRegistry.get(command).processCommand(methodSender, message, parameters, commandState);
                }
            } else {
                // Continue some command with simple message
                String command = commandState.identifier();

                if (command != null && commandRegistry.containsKey(command)) {
                    return commandRegistry.get(command).processCommand(methodSender, message, new String[0], commandState);
                }
            }
        }
        return null;
    }

    private CommandState<?> executeCommand(CallbackQuery callbackQuery, MethodSender methodSender, CommandState<?> commandState) {
        String callbackQueryId = callbackQuery.getId();
        if (!(callbackQuery.getMessage() instanceof Message message)) {
            return null;
        }
        String commandMessage = callbackQuery.getData();
        String[] parameters = new String[]{callbackQueryId};
        if (commandMessage != null) {
            String[] splitCommandParams = commandMessage.split("\\s+");
            parameters = Arrays.copyOf(parameters, 1 + splitCommandParams.length);
            System.arraycopy(splitCommandParams, 0, parameters, 1, splitCommandParams.length);
        }

        String command = commandState.identifier();

        if (command != null && commandRegistry.containsKey(command)) {
            return commandRegistry.get(command).processCommand(methodSender, message, parameters, commandState);
        }
        return null;
    }

    private boolean checkCommandState(CommandState<?> commandState, long userId, UserActivity userActivity) {
        if (commandState != null) {
            if (commandState.state() != null) {
                userActivity.setCommandState(commandState);
                userActivityHandler.saveUserActivity(userId, userActivity);
            } else {
                userActivityHandler.removeUserActivity(userId);
            }
            return true;
        } else {
            userActivityHandler.removeUserActivity(userId);
            return false;
        }
    }

    private boolean validateUserActivity(UserActivity userActivity) {
        LocalDateTime now = LocalDateTime.now();
        if (now.minusMinutes(userInactivityMaxTime).isBefore(userActivity.getLastActivity())) {
            userActivity.setLastActivity(now);
            return true;
        }
        return false;
    }

    private boolean isMessageCommand(Message message) {
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
