package usecases;
import library.modules.loginSystem.loginEntities.IUserBase;
import library.modules.loginSystem.loginEntities.RegUser;
import library.modules.loginSystem.loginEntities.User;
import library.modules.loginSystem.loginEntities.UserBase;
import library.modules.loginSystem.loginUseCases.UserManager;
import library.presenters.ConsolePresenter;
import library.presenters.IPresenter;
import library.util.IObserverManager;
import library.util.ObserverManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserManagerTest {
    @Test
    public void setup() {
        User r = new RegUser("Umared", "password") {};
        assertEquals("Umared", r.getUsername());
    }

    @Test
    public void TestCreateRegUser(){
        IUserBase ub = new UserBase();
        IPresenter presenter = new ConsolePresenter();
        IObserverManager obMan = new ObserverManager(ub);
        UserManager um = new UserManager(ub, obMan, presenter);

        um.createRegUser("user1", "password");
        um.createRegUser("user1", "password2");

        assertEquals(2, ub.getAllUsers().size());
        assertEquals("password", um.getUser("user1").getPassword());
    }

    @Test
    public void TestBanUser(){
        IUserBase ub = new UserBase();
        IPresenter presenter = new ConsolePresenter();
        IObserverManager obMan = new ObserverManager(ub);
        UserManager um = new UserManager(ub, obMan, presenter);

        um.createRegUser("user1", "password");
        um.createAdminUser("user2", "password2", "ADMIN");

        um.banUser("user2", "user1");
        um.banUser("user1", "user2");

        assertFalse(um.getUser("user2").getBanStatus());
        assertTrue(um.getUser("user1").getBanStatus());
    }
}
