package library.modules.loginSystem.loginControllers;

import library.modules.loginSystem.loginUseCases.UserManager;

/**
 * UserController provides access to control anything related to the
 * user object.
 *
 * @author group_0236
 */
public class UserController {
    //variables
    private final UserManager um;

    /**
     * Creates a UserController instance.
     * @param um UserManager
     */
    public UserController(UserManager um){
        this.um = um;
    }

    /**
     * Bans the user from the system.
     * <p>
     *     Only Admins can ban users.
     * </p>
     * @param username String
     * @param currentUser String
     * @return boolean
     */
    public boolean banUser(String username, String currentUser){
        return um.banUser(username, currentUser);
    }

    /**
     * Creates an admin user.
     * <p>
     *     Only Admins can create admins.
     * </p>
     * @param username String
     * @param password String
     * @param currentUser String
     * @return boolean
     */
    public boolean createAdminUser(String username, String password, String currentUser){
        return um.createAdminUser(username, password, currentUser);
    }

    /**
     * Create a regular user.
     * @param username String
     * @param password String
     * @return boolean
     */
    public boolean createRegUser(String username, String password){
        return um.createRegUser(username, password);
    }

    /**
     * Delete the user from the system.
     * <p>
     *     Only Admins can delete users.
     * </p>
     * @param username String
     * @param currentUser String
     * @return boolean
     */
    public boolean deleteUser(String username, String currentUser){
        return um.deleteUser(username, currentUser);
    }
}


