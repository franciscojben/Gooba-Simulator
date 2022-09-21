package library.consoleControllers;

import library.modules.loginSystem.loginUseCases.LoginManager;
import library.modules.tradeSystem.tradeControllers.TradeController;
import library.util.interfaces.IMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Console menu for actions on individual trade requests
 *
 * @author group_0236
 */
public class TradeRequestConsoleController implements IMenu {
    private final int tradeIndex;
    private final LoginManager lm;
    private final  TradeController tc;

    /**
     * Create an instance of TradeRequestConsoleController
     *
     * @param tradeIndex the index of the trade request being acted on
     * @param lm Login Manager
     * @param tc Trade Controller
     */
    public TradeRequestConsoleController(int tradeIndex, LoginManager lm, TradeController tc){
        this.tradeIndex = tradeIndex;
        this.lm = lm;
        this.tc = tc;
    }

    /**
     * Console menu for actions on individual trade requests
     * @throws IOException buffered reader exception
     */
    @Override
    public void menu() throws IOException {

        System.out.println("Select an option:");
        if(tc.isIncoming(tradeIndex, lm.getCurrentUser())){
            incomingTradeMenu();
        }else{
            outgoingTradeMenu();
        }
    }

    /**
     * Allows the user to accept or decline this trade request
     * @throws IOException buffered reader exception
     */
    private void incomingTradeMenu() throws IOException {
        String[] options = {"1-Accept Trade", "2-Decline Trade", "3-Close Menu"};
        boolean running = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(running){
            for(String option : options) {
                System.out.println(option);
            }
            String option = br.readLine();
            switch (option) {
                case "1" :
                    acceptTrade();
                    running = false;
                    break;
                case "2" :
                    cancelTrade();
                    running = false;
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
     * Allows the user to interact with or cancel an outgoing trade request
     * @throws IOException buffered reader exception
     */
    private void outgoingTradeMenu() throws IOException {
        String[] options = {"1-Cancel Trade", "2-Close Menu"};
        boolean running = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(running){
            for(String option : options) {
                System.out.println(option);
            }
            String option = br.readLine();
            switch (option) {
                case "1" :
                    cancelTrade();
                    running = false;
                    break;
                case "2" :
                    running = false;
                    break;
                default : System.out.println("Oops! Please select a valid option.");
            }
        }
    }

    /**
     * Helper function that accepts a trade
     */
    private void acceptTrade(){
        tc.acceptTrade(tradeIndex, lm.getCurrentUser());
    }

    /**
     * Helper function that cancels a trade
     */
    private void cancelTrade(){
        tc.cancelTrade(tradeIndex, lm.getCurrentUser());
    }
}
