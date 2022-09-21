package library.consoleControllers;

import library.util.interfaces.IMenu;
import library.modules.loginSystem.loginUseCases.LoginManager;
import library.modules.notificationSystem.notificationUseCases.NotificationManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class is responsible for controlling actions related to notifications.
 *
 * @author franc
 */
public class NotificationController implements IMenu {
    private final NotificationManager nm;
    private final LoginManager lm;

    /**
     * Create an instance of the NotificationController.
     * @param nm NotificationManager
     * @param lm LoginManager
     */
    public NotificationController(NotificationManager nm, LoginManager lm) {
        this.nm = nm;
        this.lm = lm;
    }

    /**
     * Displays to the user their current notification count
     */
    private void notificationCount() {
        System.out.println("You have " + lm.getCurrentUser().getAccountData().getNotificationCount()
                + " notification(s)!");
    }

    /**
     * Console menu for the Notification module
     * @throws IOException buffered reader exception
     */
    @Override
    public void menu() throws IOException {
        notificationCount();
        String[] options = {"1-See your latest notification", "2-Close menu"};
        System.out.println("Please select an option:");
        boolean running = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (running) {
            for (String option : options) {
                System.out.println(option);
            }
            String choice = br.readLine();
            switch (choice) {
                case "1" :
                    nm.showNotification(lm.getCurrentUser().getUsername());
                    break;
                case "2" :
                    running = false;
                    break;
                default :
                    System.out.println("Oops! Please select a valid option (press 1 or 2)!");
            }
        }
    }
}
