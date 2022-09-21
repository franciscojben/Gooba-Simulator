package usecases;
import static org.junit.Assert.*;

import library.modules.followSystem.followUseCases.FollowManager;
import library.modules.loginSystem.loginEntities.IUserBase;
import library.modules.loginSystem.loginEntities.RegUser;
import library.modules.loginSystem.loginEntities.User;
import library.modules.loginSystem.loginEntities.UserBase;
import library.presenters.ConsolePresenter;
import library.presenters.IPresenter;
import library.util.IObserverManager;
import library.util.ObserverManager;
import org.junit.*;
public class FollowManagerTest {
    @Test
    public void TestFollowUser(){
        IUserBase ub = new UserBase();
        User user1 = new RegUser("user1", "password");
        User user2 = new RegUser("user2", "password");
        ub.addUser(user1);
        ub.addUser(user2);
        IPresenter presenter = new ConsolePresenter();
        IObserverManager obMan = new ObserverManager(ub);
        FollowManager fm = new FollowManager(ub, obMan, presenter);

        fm.followUser("user1", "user2");

        assertEquals(0, user2.getFollowers().size());
        assertEquals(1, user1.getFollowers().size());
        assertEquals("user2", user1.getFollowers().get(0));
    }

    @Test
    public void TestUnfollowUser(){
        IUserBase ub = new UserBase();
        User user1 = new RegUser("user1", "password");
        User user2 = new RegUser("user2", "password");
        ub.addUser(user1);
        ub.addUser(user2);
        IPresenter presenter = new ConsolePresenter();
        IObserverManager obMan = new ObserverManager(ub);
        FollowManager fm = new FollowManager(ub, obMan, presenter);

        fm.followUser("user1", "user2");
        fm.followUser("user2", "user1");
        fm.unfollowUser("user1", "user2");

        assertEquals(1, user2.getFollowers().size());
        assertEquals(0, user1.getFollowers().size());
        assertEquals("user1", user2.getFollowers().get(0));
    }
}
