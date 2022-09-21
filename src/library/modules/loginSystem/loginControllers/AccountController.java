package library.modules.loginSystem.loginControllers;

import library.modules.loginSystem.loginUseCases.DataManager;
import library.modules.loginSystem.loginUseCases.AccountManager;
import library.modules.notificationSystem.notificationUseCases.NotificationManager;

public class AccountController {
    // variables
    private final DataManager dm;
    private final AccountManager am;
    private final NotificationManager nm;

    public AccountController(AccountManager am, DataManager dm, NotificationManager nm){
        this.dm = dm;
        this.am = am;
        this.nm = nm;
    }

    /**
     * Show the user's history data.
     * @param username String
     */
    public void showHistoryData(String username){
        dm.getHistoryData(username);
    }

    /**
     * Show the user's account data.
     * <p>
     *     This shows the account name and ID.  Likes, follows, notifications, and other
     *     related data is shown through their respective menus.
     * </p>
     * @param username String
     */
    public void showAccountData(String username){
        dm.getAccountData(username);
    }

    public void checkLatestHistory(String username){
        am.getLatestHistory(username);
    }
    public void likeUser(String username, String currentUser){
        am.likeUser(username, currentUser);
        nm.makeNotification("Like", username);
    }

    public void unlikeUser(String username, String currentUser){
        am.unLikeUser(username,  currentUser);
    }

    public void checkLikes(String username){
        am.checkLikes(username);
    }

    public void favouriteUser(String user1, String user2) {
        am.addFavourite(user1, user2);
    }

    public void checkFavourite(String user) {
        am.checkFavourite(user);
    }


}
