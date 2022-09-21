package library.consoleControllers;


import library.modules.loginSystem.loginControllers.LoginSystem;
import library.modules.loginSystem.loginControllers.UserController;
import library.modules.loginSystem.loginUseCases.LoginManager;
import library.programBuilder.ControllerComponent;
import library.programBuilder.ManagerComponent;
import library.util.interfaces.IMenu;

import java.io.*;

/**
 * This class is a central tradeControllers that is the listening point for the user
 * and stores all the sub loginControllers and calls their methods according to user's actions.
 * There are several menu methods which display options to the user.
 * There are also action methods that are called depending on user input.
 * @author group_0236
 */

public class MainConsole implements IMenu, MainController {
    private final LoginSystem ls;
    private final LoginManager lm;
    private final UserController uc;
    private final PetConsoleController pcc;
    private final FollowConsoleController fcc;
    private final ProfileConsoleController prc;
    private final LeaderboardConsoleController lbcc;
    private final NotificationController nc;
    private final TradeConsoleController tcc;

    /**
     * Creates an instance of the Main Console
     * @param man Manager Component
     * @param con Controller Component
     */
    public MainConsole(ManagerComponent man, ControllerComponent con) {
        lm = man.getLm();
        ls = new LoginSystem(man.getUm(), man.getUb(), man.getLm());
        uc = new UserController(man.getUm());
        fcc = new FollowConsoleController(con.getFc(), man.getLm());
        prc = new ProfileConsoleController(con.getAc(), man.getLm());
        lbcc = new LeaderboardConsoleController(con.getLbc());
        pcc = new PetConsoleController(con.getPc(), man.getLm(), man.getAm());
        nc = new NotificationController(man.getNm(), man.getLm());
        tcc = new TradeConsoleController(con.getTrc(), man.getLm());
    }


    /**
     * Runs the main menu
     * @throws IOException buffered reader exception
     */
    @Override
    public void run() throws IOException{
        menu();
    }

    /**
     * Console menu for the main first menu
     * @throws IOException buffered reader exception
     */
    @Override
    public void menu() throws IOException {
        boolean running = true;
        String[] options = {"1-Login",
                            "2-Sign Up",
                            "3-Exit"};

        System.out.println("Select an option:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(running){
            for(String option : options) {
                System.out.println(option);
            }
            String option = br.readLine();
            switch (option) {
                case "1" :
                    login();
                    break;
                case "2" :
                    signUp();
                    break;
                case "3" :
                    running = false;
                    exitProgram();
                    break;
                default :
                    System.out.println("Oops! Please select a valid option.");
            }
        }
    }

    /**
     * Prompts the user to login. Checks whether the user exists and if the username and password match.
     * @throws IOException buffered reader exception
     */
    private void login() throws IOException {
        boolean running = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(running) {
            System.out.println("Please enter your username:");
            String username = br.readLine();
            System.out.println("Please enter your password:");
            String password = br.readLine();

            if (ls.login(username, password)) {
                if (ls.adminLoggedIn()) {
                    adminMenu();
                } else {
                    regularMenu();
                }
            }
            else {
                System.out.println("Please try again.\n");
                running = false;
            }
        }
    }

    /**
     * Lets the user create a new account.
     * @throws IOException buffered reader exception
     */
    private void signUp() throws IOException {
        boolean running = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(running) {
            System.out.println("Please enter your username:");
            String username = br.readLine();
            System.out.println("Please enter your password:");
            String password = br.readLine();

            if (uc.createRegUser(username, password)){
                break;
            }
            else {
                System.out.println("Unexpected Error.");
                running = false;
            }
        }
    }

    /**
     * The options menu for an Admin User
     * @throws IOException buffered reader exception
     */
    private void adminMenu() throws IOException {
        String[] options = {
                "1-Create Admin User",
                "2-Pet Menu",
                "3-Delete User",
                "4-Ban User",
                "5-Follow Menu",
                "6-Profile Menu",
                "7-Notifications Menu",
                "8-Trade Menu",
                "9-Leaderboards",
                "10-Logout",
                "11-Exit"};

        System.out.println("Select an option:");
        boolean running = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(running){
            for(String option : options) {
                System.out.println(option);
            }
            String option = br.readLine();
            switch (option) {
                case "1" :
                    createAdminUser();
                    break;
                case "2":
                    pcc.menu();
                    break;
                case "3" :
                    deleteUser();
                    break;
                case "4" :
                    banUser();
                    break;
                case "5" :
                    fcc.menu();
                    break;
                case "6" :
                    prc.menu();
                    break;
                case "7" :
                    nc.menu();
                    break;
                case "8":
                    tcc.menu();
                    break;
                case "9":
                    lbcc.menu();
                    break;
                case "10" :
                    logout();
                    break;
                case "11" :
                    running = false;
                    exitProgram();
                    break;
                default :
                    System.out.println("Oops! Please select a valid option.");
            }
        }
    }

    /**
     * The options menu for a Regular User
     * @throws IOException buffered reader exception
     */
    private void regularMenu() throws IOException {
        String[] options = {
                "1-Pet Menu", "2-Follow Menu", "3-Profile Menu", "4-Notifications Menu", "5-Trade Menu",
                "6-Leaderboards","7-Logout", "8-Exit"};

        System.out.println("Select an option:");

        boolean running = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(running){
            for(String option : options) {
                System.out.println(option);
            }
            String option = br.readLine();
            switch (option) {
                case "1":
                    pcc.menu();
                    break;
                case "2" :
                    fcc.menu();
                    break;
                case "3" :
                    prc.menu();
                    break;
                case "4" :
                    nc.menu();
                    break;
                case "5":
                    tcc.menu();
                    break;
                case "6" :
                    lbcc.menu();
                    break;
                case "7" :
                    logout();
                    break;
                case "8" :
                    running = false;
                    exitProgram();
                    break;
                default :
                    System.out.println("Oops! Please select a valid option.");
            }
        }
    }

    /**
     * Allows an admin user to create another instance of an admin user
     * @throws IOException buffered reader exception
     */
    private void createAdminUser() throws IOException {
        boolean running = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(running){
            System.out.println("Please enter new user's username:");
            String username = br.readLine();
            System.out.println("Please enter new user's password:");
            String password = br.readLine();

            if(uc.createAdminUser(username, password, lm.getCurrentUser().getUsername())){
                adminMenu();
            }
            else {
                System.out.println("Unexpected Error");
                running = false;
            }
        }
    }

    /**
     * Permanently deletes a user. Only accessible to an admin user
     * @throws IOException buffered reader exception
     */
    private void deleteUser() throws IOException {
        boolean running = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(running){
            System.out.println("Please enter the username you'd like to delete:");
            String username = br.readLine();

            if(uc.deleteUser(username, lm.getCurrentUser().getUsername())){
                adminMenu();
            }
            else {
                System.out.println("Unexpected Error");
                running = false;
            }
        }
    }

    /**
     * Bans a user. Said user's information is still saved in the program.
     * Only accessible to an admin user
     * @throws IOException buffered reader exception
     */
    private void banUser() throws IOException {
        boolean running = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(running){
            System.out.println("Please enter the username you'd like to ban:");
            String username = br.readLine();

            if(uc.banUser(username, lm.getCurrentUser().getUsername())){
                adminMenu();
            }
            else {
                System.out.println("Unexpected Error");
                running = false;
            }
        }
    }

    /**
     * Logs the user out of the program
     * @throws IOException buffered reader exception
     */
    private void logout() throws IOException {
        ls.logout();
        menu();
    }

    /**
     * Exits the program
     */
    private void exitProgram() {
        System.exit(0);
    }


}
