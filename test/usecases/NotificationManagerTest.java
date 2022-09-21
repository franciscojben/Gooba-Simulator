package usecases;

import library.modules.loginSystem.loginEntities.IUserBase;
import library.modules.loginSystem.loginEntities.RegUser;
import library.modules.loginSystem.loginEntities.User;
import library.modules.loginSystem.loginEntities.UserBase;
import library.modules.notificationSystem.NoNotificationException;
import library.modules.notificationSystem.notificationEntities.notificationTypes.FollowNotification;
import library.modules.notificationSystem.notificationEntities.notificationTypes.LikeNotification;
import library.modules.notificationSystem.notificationUseCases.NotificationManager;
import library.presenters.ConsolePresenter;
import library.presenters.IPresenter;
import library.util.IObserverManager;
import library.util.ObserverManager;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NotificationManagerTest {
    @Test
    public void TestMakeNotification() throws NoNotificationException {
        IUserBase ub = new UserBase();
        User user1 = new RegUser("user1", "password");
        User user2 = new RegUser("user2", "password");
        ub.addUser(user1);
        ub.addUser(user2);
        IPresenter presenter = new ConsolePresenter();
        IObserverManager obMan = new ObserverManager(ub);
        NotificationManager nm = new NotificationManager(ub, obMan, presenter);

        nm.makeNotification("Like", "user1");
        nm.makeNotification("Follow", "user2");

        assertEquals(1, user1.getAccountData().getNotificationCount());
        assertEquals(1, user2.getAccountData().getNotificationCount());
        assertTrue(user2.getAccountData().getNotification() instanceof FollowNotification);
        assertTrue(user1.getAccountData().getNotification() instanceof LikeNotification);
    }

}
