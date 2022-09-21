package library.modules.notificationSystem.notificationUseCases;

import library.modules.notificationSystem.NotificationFactory;
import library.modules.notificationSystem.notificationEntities.Notification;
import library.util.IObserverManager;
import library.modules.loginSystem.loginEntities.IUserBase;
import library.modules.notificationSystem.NoNotificationException;
import library.presenters.IPresenter;

/**
 * The NotificationManager class denotes the use cases of anything related to Notifications.
 *
 * @author franc
 */
public class NotificationManager {
    final IUserBase userBase;
    final IObserverManager obMan;
    final IPresenter presenter;

    /**
     * Instantiate NotificationManager.
     * @param presenter IPresenter
     */
    public NotificationManager(IUserBase userBase, IObserverManager obMan, IPresenter presenter) {
        this.userBase = userBase;
        this.obMan = obMan;
        this.presenter = presenter;
    }

    /**
     * Make a notification with a specific type and store it in the user.
     * @param notificationType String
     */
    public void makeNotification(String notificationType, String user) {
        NotificationFactory factory = new NotificationFactory();
        Notification notification = factory.makeNotification(notificationType);
        if (userBase.isUser(user)) {
            userBase.getUser(user).getAccountData().addNotification(notification);
            obMan.updateUsers();
        }
    }

    /**
     * Show the user's notification.
     * <p>
     * Return true iff the user has a notification.
     * </p>
     *
     * @param user String
     */
    public void showNotification(String user) {
        try {
            presenter.present(userBase.getUser(user).getAccountData().getNotification().getMessage());
        } catch (NoNotificationException exception) {
            presenter.present(exception.toString());
        }
    }
}
