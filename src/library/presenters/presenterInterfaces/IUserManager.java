package library.presenters.presenterInterfaces;

/**
 * Interface for UserManager
 *
 * @author group_0236
 */
public interface IUserManager {

    /**
     * Response for when a user logs in
     * @param response String
     */
    void responseLogin(String response);

    /**
     * Response in relation to changing aspects about the user
     * @param response String
     */
    void responseUser(String response);

}
