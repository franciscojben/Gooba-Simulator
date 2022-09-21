package library.presenters.presenterInterfaces;

/**
 * Interface for AccountManager
 *
 * @author group_0236
 */
public interface IAccountManager {
    /**
     * Show a String
     * @param show String
     */
    void show(String show);

    /**
     * Response for liking or unliking a user
     * @param response String
     */
    void responseLike(String response);

    /**
     * Response for favoring a user
     * @param response String
     */
    void responseFavourite(String response);

}