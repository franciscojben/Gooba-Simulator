package library.modules.leaderboardSystem.leaderboardControllers;

import library.modules.leaderboardSystem.leaderboardUseCases.LeaderboardManager;


/**
 * Creates a Leaderboard for the User
 *
 * @author group_0236
 */
public class LeaderboardController {

    private final LeaderboardManager lbm;

    /**
     * Create an instance of the Leaderboard Controller.
     *
     * @param lbm LeaderboardManager
     */
    public LeaderboardController(LeaderboardManager lbm){
        this.lbm = lbm;
    }

    /**
     * Get the death leaderboard.
     */
    public void getDeathLeaderboard(){
        lbm.getLeaderboard("Death");
    }

    /**
     * Get the likes leaderboard.
     */
    public void getLikesLeaderboard(){
        lbm.getLeaderboard("Likes");
    }

}
