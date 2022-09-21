package library.consoleControllers;

import library.modules.loginSystem.loginUseCases.LoginManager;
import library.modules.tradeSystem.tradeControllers.TradeController;
import library.util.interfaces.IMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/** The TradeConsoleController determines actions that the user can take on the trading module
 *
 * @author group_0236
 */
public class TradeConsoleController implements IMenu {
    private final TradeController tc;
    private final LoginManager lm;

    /**
     * Create an instance of TradeConsoleController
     * @param tc Trade Controller
     * @param lm Login Manager
     */
    public TradeConsoleController(TradeController tc, LoginManager lm){
        this.tc = tc;
        this.lm = lm;
    }

    /**
     * Console menu for the Trading module
     * @throws IOException buffered reader exception
     */
    @Override
    public void menu() throws IOException {
        String[] options = {"1-View Trade Requests", "2-Send Trade", "3-Close Menu"};

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
                    viewTradeRequests();
                    break;
                case "2" :
                    sendTrade();
                    break;
                case "3" :
                    running = false;
                    break;
                default :
                    System.out.println("Oops! Please select a valid option.");
                    break;
            }
        }
    }

    /**
     * Allows the user to send a trade request to another user of their choice
     * @throws IOException buffered reader exception
     */
    private void sendTrade() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean running = true;
        while(running) {
            System.out.println("Who would you like to swap your pet with?");
            String otherUser = br.readLine();
            if (Objects.equals(otherUser, "exit")){
                running = false;
            }
            else if (tc.sendTrade(lm.getCurrentUser(), otherUser)) {
                this.menu();
                running = false;
            } else {
                System.out.println("Please enter a valid username.");
            }
        }
    }

    /**
     * Allows the user to interact with their received trade requests
     * @throws IOException buffered reader exception
     */
    private void viewTradeRequests() throws IOException {
        boolean running = true;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(running & tc.viewTradeRequests(lm.getCurrentUser())) {
            System.out.println("Select a trade request to interact with or type exit to cancel.");
            String index = br.readLine();
            if (index.equals("exit")){
                running = false;
            }
            else if (tc.isValid(Integer.parseInt(index), lm.getCurrentUser())) {
                // A new instance of trc is made each time since it is unique to each trade request index
                TradeRequestConsoleController trc = new TradeRequestConsoleController(Integer.parseInt(index), this.lm, this.tc);
                trc.menu();
            } else {
                System.out.println("Please select a valid trade request.");
            }
        }
    }
}
