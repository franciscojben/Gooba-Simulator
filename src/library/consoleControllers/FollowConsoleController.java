package library.consoleControllers;

import library.util.interfaces.IMenu;
import library.modules.followSystem.followControllers.FollowController;
import library.modules.loginSystem.loginUseCases.LoginManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Controls all user following functionality.
 *
 * @author group_0236
 */
public class FollowConsoleController implements IMenu {
    private final FollowController fc;
    private final LoginManager lm;

    /**
     * Create an instance of the FollowConsoleController.
     * @param fc FollowController
     * @param lm LoginManager
     */
    public FollowConsoleController(FollowController fc, LoginManager lm) {
        this.fc = fc;
        this.lm = lm;
    }

    /**
     *
     * @return Returns user input(name)
     * @throws IOException for buffered-reader
     */
    private String selectUser() throws IOException {
        System.out.println("What is the user's name?");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    /**
     * Console menu for the Follow module
     * @throws IOException buffered reader exception
     */
    @Override
    public void menu() throws IOException {
        String[] options = {"1-Follow a user", "2-Unfollow a user", "3-Check who you follow",
                "4-Check who follows you", "5-Close Menu"};
        System.out.println("Select an option:");
        boolean running = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (running) {
            for (String option : options) {
                System.out.println(option);
            }
            String choice = br.readLine();
            switch (choice) {
                case "1" :
                    String name1 = selectUser();
                    fc.follow(lm.getCurrentUser().getUsername(), name1);
                    break;
                case "2" :
                    String name2 = selectUser();
                    fc.unfollow(lm.getCurrentUser().getUsername(), name2);
                    break;
                case "3" :
                    fc.getFollowers(lm.getCurrentUser().getUsername());
                    break;
                case "4" :
                    fc.getFollowing(lm.getCurrentUser().getUsername());
                    break;
                case "5" :
                    running = false;
                    break;
                default :
                    System.out.println("Oops! Please select a valid option.");
            }
        }
    }
}
