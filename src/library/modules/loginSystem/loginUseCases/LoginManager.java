package library.modules.loginSystem.loginUseCases;

import library.modules.loginSystem.loginEntities.User;
import library.util.IObserverManager;
import library.presenters.presenterInterfaces.ILoginManager;

import java.util.Date;

/**
 * Enables user to authenticate in and logout when done
 * Used to save all the login and logout records
 */
public class LoginManager {

    private User currentUser;
    private final IObserverManager obMan;
    private final ILoginManager presenter;

    /**
     * Creating instances
     * @param obMan IObserverManager
     * @param presenter ILoginManager
     */
    public LoginManager(IObserverManager obMan, ILoginManager presenter) {
        this.obMan = obMan;
        this.presenter = presenter;
    }

    /**
     * Saves users logins
     * @param user User
     */
    public void recordLogin(User user) {
        String loginDate = String.valueOf(Date.from(java.time.Clock.systemUTC().instant()));
        user.addHistoryData(loginDate);
        obMan.updateUsers();
    }

    /**
     * Enables the user to login
     * @param user User
     */
    public void login(User user){
        this.recordLogin(user);
        this.setCurrentUser(user);
        obMan.loginEvent(user);
    }

    /**
     * Enables the user to log out
     */
    public void logout(){
        obMan.logoutEvent(this.currentUser);
        this.currentUser = null;
        presenter.responseLogout("Logged out.");
    }

    /**
     * Returns current user
     * @return User User
     */
    public User getCurrentUser() {
        return this.currentUser;
    }

    /**
     * Sets current user
     * @param user User
     */
    public void setCurrentUser(User user){
        this.currentUser = user;
    }
}
