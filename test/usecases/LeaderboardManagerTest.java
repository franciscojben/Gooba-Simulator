package usecases;

import library.modules.loginSystem.loginEntities.IUserBase;
import library.modules.loginSystem.loginEntities.RegUser;
import library.modules.loginSystem.loginEntities.User;
import library.modules.loginSystem.loginEntities.UserBase;
import library.modules.loginSystem.loginUseCases.AccountManager;
import library.presenters.ConsolePresenter;
import library.presenters.IPresenter;
import library.util.IObserverManager;
import library.util.ObserverManager;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeaderboardManagerTest {

    @Test
    public void TestLikeUser(){
        IUserBase ub = new UserBase();
        User user1 = new RegUser("user1", "password");
        User user2 = new RegUser("user2", "password");
        ub.addUser(user1);
        ub.addUser(user2);
        IPresenter presenter = new ConsolePresenter();
        IObserverManager obMan = new ObserverManager(ub);
        AccountManager am = new AccountManager(ub, obMan, presenter);

        am.likeUser("user1", "user2");

        assertEquals(0, user2.getAccountData().getNumLikes());
        assertEquals(1, user1.getAccountData().getNumLikes());
    }

    @Test
    public void TestUnlikeUser(){
        IUserBase ub = new UserBase();
        User user1 = new RegUser("user1", "password");
        User user2 = new RegUser("user2", "password");
        ub.addUser(user1);
        ub.addUser(user2);
        IPresenter presenter = new ConsolePresenter();
        IObserverManager obMan = new ObserverManager(ub);
        AccountManager am = new AccountManager(ub, obMan, presenter);

        am.likeUser("user1", "user2");
        am.likeUser("user2", "user1");
        am.unLikeUser("user1", "user2");

        assertEquals(0, user1.getAccountData().getNumLikes());
        assertEquals(1, user2.getAccountData().getNumLikes());
    }

}
