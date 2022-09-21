package library.consoleControllers;

import library.util.interfaces.IMenu;
import library.modules.leaderboardSystem.leaderboardControllers.LeaderboardController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The LeaderboardConsoleController determines all actions related to the leaderboardSystem feature
 *
 * @author group_0236
 */
public class LeaderboardConsoleController implements IMenu {
    //variables
    private final LeaderboardController lc;

    /**
     * Create an instance of the LeaderboardConsoleController
     * @param lc Leaderboard Controller
     */
    public LeaderboardConsoleController(LeaderboardController lc){
        this.lc = lc;
    }

    /**
     * Console menu for the leaderboard module
     * @throws IOException buffered reader exception
     */
    @Override
    public void menu() throws IOException {
        String[] options = {"1-Likes",
                "2-Deaths", "3-Close Menu"};
        System.out.println("Select a leaderboardSystem to view:");

        boolean running = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(running) {
            for(String option : options) {
                System.out.println(option);
            }
            String option = br.readLine();
            switch (option) {
                case "1":
                    lc.getLikesLeaderboard();
                    break;
                case "2":
                    lc.getDeathLeaderboard();
                    break;
                case "3":
                    running = false;
                    break;
                default :
                    System.out.println("That isn't a current leaderboardSystem option!");
            }
        }

    }


}
