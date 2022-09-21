package library.consoleControllers;

import library.util.interfaces.IMenu;
import library.modules.loginSystem.loginControllers.AccountController;
import library.modules.loginSystem.loginUseCases.LoginManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** The ProfileConsoleController determines actions that the user can take on the system.
 *
 * @author group_0236
 */
public class ProfileConsoleController implements IMenu {
    private final AccountController ac;
    private final LoginManager lm;

    /**
     * Create the tradeControllers to show the user's profile.
     * @param ac AccountController
     * @param lm LoginManager
     */
    public ProfileConsoleController(AccountController ac, LoginManager lm) {
        this.ac = ac;
        this.lm = lm;
    }

    /**
     * Console menu for the Profile module
     * @throws IOException buffered reader exception
     */
    @Override
    public void menu() throws IOException {
        String[] options = {"1-Like a user", "2-Unlike a user", "3-Favourite a user",
                "4-Check your likes", "5-Check your favourite", "6-Check a user's favourite",
                "7-View most recent login", "8-View History data", "9-View Account data", "10-Close Menu"};
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
                    ac.likeUser(name1, lm.getCurrentUser().getUsername());
                    break;
                case "2" :
                    String name2 = selectUser();
                    ac.unlikeUser(name2, lm.getCurrentUser().getUsername());
                    break;
                case "3" :
                    String name3 = selectUser();
                    ac.favouriteUser(lm.getCurrentUser().getUsername(), name3);
                    break;
                case "4" :
                    ac.checkLikes(lm.getCurrentUser().getUsername());
                    break;
                case "5" :
                    ac.checkFavourite(lm.getCurrentUser().getUsername());
                    break;
                case "6" :
                    String name6 = selectUser();
                    ac.checkFavourite(name6);
                    break;
                case "7" :
                    ac.checkLatestHistory(lm.getCurrentUser().getUsername());
                    break;
                case "8" :
                    ac.showHistoryData(lm.getCurrentUser().getUsername());
                    break;
                case "9" :
                    ac.showAccountData(lm.getCurrentUser().getUsername());
                    break;
                case "10" :
                    running = false;
                    break;
                default :
                    System.out.println("Oops! Please select a valid option.");
            }
        }
    }

    /**
     * Helper function that prompts the user to input another user's name
     * @return String
     * @throws IOException buffered reader exception
     */
    private String selectUser() throws IOException {
        System.out.println("What is the user's name?");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }
}
