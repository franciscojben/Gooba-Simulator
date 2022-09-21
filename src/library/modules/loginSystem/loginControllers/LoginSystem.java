package library.modules.loginSystem.loginControllers;

import library.modules.loginSystem.loginEntities.IUserBase;
import library.modules.loginSystem.loginUseCases.LoginManager;
import library.modules.loginSystem.loginUseCases.UserManager;

/**
 * LoginSystem manages the control functionality for logging in, logging out, and more.
 *
 * @author group_0236
 */
public class LoginSystem {

    private final IUserBase userBase;
    private final UserManager um;
    private final LoginManager lm;

    public LoginSystem(UserManager um, IUserBase userBase, LoginManager lm) {
        this.um = um;
        this.userBase = userBase;
        this.lm = lm;
    }
    public boolean login(String username, String password) {
        if (um.verifyUser(username, password)){
            lm.login(userBase.getUser(username));
            return true;
        }
        return false;
    }

    public boolean adminLoggedIn(){
        if (lm.getCurrentUser() != null){
            return lm.getCurrentUser().getAdminStatus();
        }
        return false;
    }

    public void logout(){
        lm.logout();
    }
}
