package library.modules.followSystem.followUseCases;

import library.modules.loginSystem.loginEntities.IUserBase;
import library.util.IObserverManager;
import library.presenters.presenterInterfaces.IFollowManager;

import java.util.List;

/**
 * The FollowManager denotes all the actions related to following users.
 *
 * @author group_0236
 */
public class FollowManager {
    private final IUserBase userBase;
    private final IObserverManager obMan;
    private final IFollowManager presenter;

    /**
     * Create an instance of FollowManager.
     * @param userBase IUserBase
     * @param obMan IObserverManager
     * @param presenter IFollowManager
     */
    public FollowManager(IUserBase userBase, IObserverManager obMan, IFollowManager presenter) {
        this.userBase = userBase;
        this.obMan = obMan;
        this.presenter = presenter;
    }

    /**
     * Follow a user.
     *
     * @param currentUser String
     * @param username    String
     */
    public void followUser(String currentUser, String username){
        String response;
        if(!userBase.isUser(username)){
            response = "User Doesn't Exist.";
        } else if (currentUser.equals(username)) {
            response = "You cannot follow yourself!";
        } else if (userBase.getUser(username).getBanStatus()){
            response = "Operation failed. User is banned.";
        } else if (userBase.getUser(username).following(userBase.getUser(currentUser))) {
            response = "You have already followed this user.";
        } else {
            userBase.getUser(username).follow(userBase.getUser(currentUser));
            response = "You have successfully followed " + username;
        }
        presenter.responseFollow(response);
        obMan.updateUsers();
    }

    /**
     * Unfollow a user.
     *
     * @param currentUser String
     * @param username    String
     */
    public void unfollowUser(String currentUser, String username) {
        String response;
        if (!userBase.isUser(username)) {
            response = "User Doesn't Exist.";
        } else if (userBase.getUser(username).getBanStatus()) {
            response = "Operation failed. User is banned.";
        } else if (!userBase.getUser(username).following(userBase.getUser(currentUser))) {
            response = "You have not followed this user.";
        } else {
            userBase.getUser(username).unfollow(userBase.getUser(currentUser));
            response = "You have successfully unfollowed " + username;
        }
        presenter.responseFollow(response);
        obMan.updateUsers();
    }

    /**
     * Present the followers list of a user.
     * @param user String
     */
    public void presentFollowers(String user) {
       List<String> followList = userBase.getUser(user).getFollowers();
       for (String e : followList) {
           presenter.responseFollow(e);
       }
    }

    /**
     * Present the following list of a user.
     * @param user String
     */
    public void presentFollowing(String user) {
        List<String> followList = userBase.getUser(user).getFollowing();
        for (String e : followList) {
            presenter.responseFollow(e);
        }
    }
}
