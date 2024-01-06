package ru.loolzaaa.tgbot4j.bot.processor.activitystate.activity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Default implementation for {@link UserActivityHandler} interface.
 * A concurrent hash map is used as a storage of user activity.
 */

public class DefaultUserActivityHandler implements UserActivityHandler {

    private final Map<Long, UserActivity> userActivityRegistry = new ConcurrentHashMap<>();

    /**
     * Load user activity from map by user id.
     *
     * @param id user id
     * @return user activity or null if storage map
     * contains no mapping for the key
     */
    @Override
    public UserActivity loadUserActivity(long id) {
        UserActivity userActivity = userActivityRegistry.get(id);
        if (userActivity != null) {
            return new UserActivity(userActivity);
        }
        return null;
    }

    /**
     * Save user activity in storage map.
     *
     * @param id           user id
     * @param userActivity activity of saving user
     */
    @Override
    public void saveUserActivity(long id, UserActivity userActivity) {
        userActivityRegistry.put(id, userActivity);
    }

    /**
     * Remove user activity from storage map.
     *
     * @param id user id
     * @return removed user activity, or null
     * if there was no mapping for key
     */
    @Override
    public UserActivity removeUserActivity(long id) {
        return userActivityRegistry.remove(id);
    }
}
