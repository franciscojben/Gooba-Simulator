package library.modules.notificationSystem.notificationEntities;

import library.modules.notificationSystem.NoNotificationException;

import java.io.Serializable;
import java.util.Stack;

/**
 * This class is responsible for storing different types of notifications.
 *
 * @author franc
 */
public class NotificationCollection implements Serializable {
    private final Stack<Notification> notifications;

    /**
     * Create an instance of NotificationCollection.
     */
    public NotificationCollection() {
        notifications = new Stack<>();
    }

    /**
     * Add a notification.
     * @param notification Notification
     */
    public void addNotification(Notification notification) {
        notifications.push(notification);
    }

    /**
     * Get the latest notification message.
     * @return Notification
     */
    public Notification getNotification() throws NoNotificationException {
        if (notifications.isEmpty()) {
            throw new NoNotificationException();
        } else {
            return notifications.pop();
        }
    }

    /**
     * Get the number of notifications.
     * @return int
     */
    public int size() {
        return notifications.size();
    }
}
