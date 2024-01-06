package ru.loolzaaa.tgbot4j.bot.processor.activitystate.command;

/**
 * This class holds the state of a command
 * along with its identifier.
 * <p>
 * The state can be an object of any type.
 * When implementing, you need to be careful
 * about unchecked casts:
 * <pre>
 * {@code
 *     @Override
 *     public CommandState<?> processMessage(AbsSender absSender, Message message, String[] arguments, CommandState<?> commandState) {
 *          CommandState<State> currentState = (CommandState<State>) commandState;
 *     }
 *
 *     private static enum State {
 *          FIRST, SECOND;
 *     }
 * }
 * </pre>
 *
 * @param <T> command state type
 */

public record CommandState<T>(String identifier, T state) {
}
