package library.modules.notificationSystem.notificationEntities.notificationTypes;

import library.modules.notificationSystem.notificationEntities.Notification;

import java.io.Serializable;

/**
 * A generic notification as a default.
 *
 * @author franc
 */
public class GenericNotification implements Notification, Serializable {
    private final String message;

    /**
     * Create an instance of a generic notification with a specified message.
     * @param message String
     */
    public GenericNotification(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
