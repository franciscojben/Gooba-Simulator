package library.modules.notificationSystem.notificationEntities.notificationTypes;

import library.modules.notificationSystem.notificationEntities.Notification;

import java.io.Serializable;

/**
 * A notification for when a user follows you.
 *
 * @author franc
 */
public class FollowNotification implements Notification, Serializable {
    @Override
    public String getMessage() {
        return "<System> A user has followed you.  Check your followers list!";
    }
}
