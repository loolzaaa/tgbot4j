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
 *     public CommandState<State> processMessage(MethodSender methodSender, Message message, String[] arguments, CommandState<?> currentState) {
 *          //CommandState<State> someState = (CommandState<State>) currentState; <--- NOT RECOMMEND
 *          State state = (State) currentState.state();
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
