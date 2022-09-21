package library.modules.notificationSystem.notificationEntities.notificationTypes;

import library.modules.notificationSystem.notificationEntities.Notification;

import java.io.Serializable;

/**
 * Notify that a user has liked you.
 *
 * @author franc
 */
public class LikeNotification implements Notification, Serializable {
    @Override
    public String getMessage() {
        return "<System> A user has liked you!";
    }
}
