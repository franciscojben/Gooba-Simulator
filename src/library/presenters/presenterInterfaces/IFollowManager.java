package library.presenters.presenterInterfaces;

/**
 * Update on users following information
 *
 * @author group_0236
 */
public interface IFollowManager {
    /**
     * Sends messages when subjected to a change in follow requests
     * @param response String
     */
    void responseFollow(String response);

}
