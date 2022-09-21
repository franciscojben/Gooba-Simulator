package usecases;
import static org.junit.Assert.*;

import library.modules.loginSystem.loginEntities.IUserBase;
import library.modules.loginSystem.loginEntities.RegUser;
import library.modules.loginSystem.loginEntities.User;
import library.modules.loginSystem.loginEntities.UserBase;
import library.modules.loginSystem.loginUseCases.LoginManager;
import library.presenters.ConsolePresenter;
import library.presenters.IPresenter;
import library.util.IObserverManager;
import library.util.ObserverManager;
import org.junit.*;
public class LoginManagerTest {
    @Test
    public void TestLogin(){
        IUserBase ub = new UserBase();
        IPresenter presenter = new ConsolePresenter();
        IObserverManager obMan = new ObserverManager(ub);
        LoginManager lm = new LoginManager(obMan, presenter);
        User user1 = new RegUser("user1", "password");
        lm.login(user1);
        assertEquals(user1, lm.getCurrentUser());
        assertEquals(2, user1.getHistoryData().getHistory().size());
    }

    @Test
    public void TestLogout(){
        IUserBase ub = new UserBase();
        IPresenter presenter = new ConsolePresenter();
        IObserverManager obMan = new ObserverManager(ub);
        LoginManager lm = new LoginManager(obMan, presenter);
        User user1 = new RegUser("user1", "password");
        lm.login(user1);
        lm.logout();
        assertNull(lm.getCurrentUser());
    }
}
