package library.modules.notificationSystem;

import library.modules.notificationSystem.notificationEntities.Notification;
import library.modules.notificationSystem.notificationEntities.notificationTypes.FollowNotification;
import library.modules.notificationSystem.notificationEntities.notificationTypes.GenericNotification;
import library.modules.notificationSystem.notificationEntities.notificationTypes.LikeNotification;

/**
 * Responsible for generating instances of different notification types.
 *
 * @author franc
 */
public class NotificationFactory {

    /**
     * Make a notification instance.
     * @param notificationType String
     * @return Notification
     */
    public Notification makeNotification(String notificationType) {
        if (notificationType.equalsIgnoreCase("Follow")) {
            return new FollowNotification();
        } else if (notificationType.equalsIgnoreCase(("Like"))) {
            return new LikeNotification();
        } else {
            return new GenericNotification("You have been notified... however we aren't sure why!");
        }
    }
}
