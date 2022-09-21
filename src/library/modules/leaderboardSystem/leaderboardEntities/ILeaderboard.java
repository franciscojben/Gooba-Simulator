package library.modules.leaderboardSystem.leaderboardEntities;

import java.util.List;

/**
 * An interface for the Leaderboard
 * @param <User>
 *
 * @author group_0236
 */
public interface ILeaderboard<User> {

    /**
     * Initialize the leaderboard
     */
    void initialize();

    /**
     * Return the Leaderboard list.
     * @return ArrayList</T>
     */
    List<User> getLbList();


}
