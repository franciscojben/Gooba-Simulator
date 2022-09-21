package library.modules.leaderboardSystem.leaderboardUseCases;

import library.modules.leaderboardSystem.DeathComparator;
import library.modules.leaderboardSystem.LikesComparator;
import library.modules.leaderboardSystem.leaderboardEntities.ILeaderboard;
import library.modules.leaderboardSystem.leaderboardEntities.Leaderboard;
import library.modules.loginSystem.loginEntities.IUserBase;
import library.modules.loginSystem.loginEntities.User;
import library.presenters.IPresenter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

/**
 * LeaderboardManager manages the different types of leaderboards currently available
 *
 * @author group_0236
 */
public class LeaderboardManager implements PropertyChangeListener {
    private final HashMap<String, ILeaderboard<User>> leaderboards = new HashMap<>();
    private final IPresenter presenter;

    /**
     * Initializes an instance of the LeaderboardManager
     * @param ub IUserBase
     * @param presenter IPresenter
     */
    public LeaderboardManager(IUserBase ub, IPresenter presenter) {
        ILeaderboard<User> deathLB = new Leaderboard<>(10, new DeathComparator(), ub);
        ILeaderboard<User> likeLB = new Leaderboard<>(10, new LikesComparator(), ub);
        leaderboards.put("Death", deathLB);
        leaderboards.put("Likes", likeLB);
        this.presenter = presenter;
    }

    /**
     * Get a leaderboard of a specified type
     * @param leaderboardType String
     */
    public void getLeaderboard(String leaderboardType){
        String response = "That leaderboard does not exist";
        if (leaderboards.containsKey(leaderboardType)) {
            StringBuilder sb = new StringBuilder(leaderboardType + " Leaderboard!\n");
            int place = 1;
            ILeaderboard<User> leaderboard = leaderboards.get(leaderboardType);
            leaderboard.initialize();
            for (User user : leaderboard.getLbList()) {
                Object attribute = "";
                String category = "";
                if (leaderboardType.equals("Death")){
                    attribute = user.getDeathCount();
                    category = " Death Count ";
                } else if (leaderboardType.equals("Likes")){
                    attribute = user.getAccountData().getAccountID();
                    category = " Number of Likes ";

                }
                String record = place + " " + user.getUsername() + category + attribute + "\n";
                sb.append(record);
                place += 1;
            }
            response = sb.toString();
        }
        presenter.responseLeaderboard(response);
    }

    /**
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("updateUsers")){
            for (ILeaderboard<User> leaderboard : leaderboards.values()){
                leaderboard.initialize();
            }
        }
    }
}
