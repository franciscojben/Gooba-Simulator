package library.modules.notificationSystem;

/**
 * Custom Exception class for when the user tries to access an empty notifications list.
 *
 * @author franc
 */

public class NoNotificationException extends Exception {
    /**
     * Get the "You have no notifications." warning.
     * @return String
     */
    @Override
    public String toString() {
        return "You have no notifications.";
    }
}
