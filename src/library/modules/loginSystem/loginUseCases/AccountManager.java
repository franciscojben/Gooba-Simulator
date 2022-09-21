package library.modules.loginSystem.loginUseCases;

import library.modules.loginSystem.loginEntities.IUserBase;
import library.modules.loginSystem.loginEntities.User;
import library.util.IObserverManager;
import library.presenters.presenterInterfaces.IAccountManager;

/**
 * AccountManager denotes all the supported functionality for dealing with account information and
 * user interaction in the system.
 *
 * @author group_0236
 */
public class AccountManager {
    final IUserBase userBase;
    final IObserverManager obMan;
    final IAccountManager presenter;

    /**
     * Create an instance of AccountManager.
     *
     * @param userBase IUserBase
     */
    public AccountManager(IUserBase userBase, IObserverManager obMan, IAccountManager presenter) {
        this.userBase = userBase;
        this.obMan = obMan;
        this.presenter = presenter;
    }

    /**
     * Get the latest history of the user.
     *
     * @param username String
     */
    public void getLatestHistory(String username) {
        presenter.show(userBase.getUser(username).getHistoryData().lastLogin());
    }

    /**
     * Like a user.
     *
     * @param username    String
     * @param currentUser User
     */
    public void likeUser(String username, String currentUser) {
        String response;
        if (!userBase.isUser(username)) {
            response = "User doesn't exist.";
        } else if (userBase.getUser(username).getBanStatus()) {
            response = "Operation failed. User is banned.";
        } else if (userBase.getUser(username).getAccountData().contains(userBase.getUser(currentUser))) {
            response = "You have already liked this user.";
        } else {
            userBase.getUser(username).getAccountData().like(userBase.getUser(currentUser));
            response = "You have successfully liked " + username;
        }
        presenter.responseLike(response);
        obMan.updateUsers();
    }

    /**
     * Unlike a user from the system.
     *
     * @param username    String
     * @param currentUser User
     */
    public void unLikeUser(String username, String currentUser) {
        String response;
        if (!userBase.isUser(username)) {
            response = "User doesn't exist.";
        } else if (userBase.getUser(username).getBanStatus()) {
            response = "Operation failed. User is banned.";
        } else if (!userBase.getUser(username).getAccountData().contains(userBase.getUser(currentUser))) {
            response = "You have not liked this user.";
        } else {
            userBase.getUser(username).getAccountData().unlike(userBase.getUser(currentUser));
            response = "You have successfully unliked " + username;
        }
        presenter.responseLike(response);
        obMan.updateUsers();
    }

    /**
     * Check the user's number of likes.
     *
     * @param username String
     */
    public void checkLikes(String username) {
        String response;
        if (!userBase.isUser(username)) {
            response = "User doesn't exist.";
        } else if (userBase.getUser(username).getBanStatus()) {
            response = "Operation failed. User is banned.";
        } else {
            response = username + " has " + userBase.getUser(username).getAccountData().getNumLikes() + " likes.";
        }
        presenter.responseLike(response);
        obMan.updateUsers();
    }

    /**
     * Add user1's favourite to be user2.  Show the result to the presenter when completed.
     *
     * @param user1 String
     * @param user2 String
     */
    public void addFavourite(String user1, String user2) {
        String response;
        if (!userBase.isUser(user1) || !userBase.isUser(user2)) {
            response = "One or more users do not exist!";
        } else if (userBase.getUser(user1).getBanStatus() || userBase.getUser(user2).getBanStatus()) {
            response = "One or more users are banned!";
        } else {
            User firstUser = userBase.getUser(user1);
            User secondUser = userBase.getUser(user2);
            firstUser.getAccountData().setFavourite(secondUser);
            response = "Made " + secondUser.getUsername() + " be "
                    + firstUser.getUsername() + "'s favourite user.";
        }
        presenter.responseFavourite(response);
        obMan.updateUsers();
    }

    /**
     * Check user's current favourite user.
     *
     * @param user String
     */
    public void checkFavourite(String user) {
        String response;
        if (!userBase.isUser(user)) {
            response = "Not a user.";
        } else if (userBase.getUser(user).getBanStatus()) {
            response = "User is banned.";
        } else if (userBase.getUser(user).getAccountData().getFavourite() == null) {
            response = "There is no favourite.";
        } else {
            response = user + "'s favourite is " + userBase.getUser(user).getAccountData().getFavourite();
        }
        presenter.responseFavourite(response);
        obMan.updateUsers();
    }

    /**
     * Set the user's pet.
     *
     * @param username String
     * @param petName  String
     */
    public void setPet(String username, String petName) {
        userBase.getUser(username).setPet(petName);
        obMan.updateUsers();
    }
    /**
     * Update the death count for the given user
     * @param username String
     */
    public void addDeathCount(String username) {
        userBase.getUser(username).addDeathCount();
        obMan.updateUsers();
    }
}
