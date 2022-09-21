package library.modules.followSystem.followControllers;

import library.modules.followSystem.followUseCases.FollowManager;
import library.modules.notificationSystem.notificationUseCases.NotificationManager;

/**
 * The FollowController dictates all follow interaction that the user manipulates.
 *
 * @author group_0236
 */
public class FollowController {
    private final FollowManager fm;
    private final NotificationManager nm;

    /**
     * Creates a FollowController instance.
     * @param fm UserManager
     */
    public FollowController(FollowManager fm, NotificationManager nm){
        this.fm = fm;
        this.nm = nm;
    }

    /**
     * Get the user's following.
     * @param user String
     */
    public void getFollowing(String user) {
        fm.presentFollowing(user);
    }

    /**
     * Get the user's followers.
     * @param user String
     */
    public void getFollowers(String user) {
        fm.presentFollowers(user);
    }

    /**
     * Follow a user.
     * @param currentUser String
     * @param otherUser String
     */
    public void follow(String currentUser, String otherUser) {
        fm.followUser(currentUser, otherUser);
        nm.makeNotification("Follow", otherUser);
    }

    /**
     * Unfollow a user.
     * @param currentUser String
     * @param otherUser String
     */
    public void unfollow(String currentUser, String otherUser) {
        fm.unfollowUser(currentUser, otherUser);
    }

}
