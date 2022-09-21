package library.consoleControllers;

import library.modules.loginSystem.loginUseCases.AccountManager;
import library.util.interfaces.IMenu;
import library.modules.loginSystem.loginUseCases.LoginManager;
import library.modules.petSystem.petControllers.PetController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * The PetConsoleController class determines actions for the Pet system.
 *
 * @author group_0236
 */
public class PetConsoleController implements IMenu {
    private final PetController pc;
    private final LoginManager lm;
    private final AccountManager am;

    public PetConsoleController(PetController pc, LoginManager lm, AccountManager am){
        this.pc = pc;
        this.lm = lm;
        this.am = am;
    }

    /**
     * Console menu for the Pet module
     * @throws IOException buffered reader exception
     */
    @Override
    public void menu() throws IOException {
        String[] options = {"1-New Pet", "2-Play",
                "3-Feed", "4-Talk", "5-See Age", "6-See Health", "7-Feed Meds", "8-Kill", "9-Bury Pet",
                "10-View Graveyard", "11-Close Menu"};

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
                    newPet();
                    break;
                case "2" :
                    pc.playWith(lm.getCurrentUser().getPet());
                    break;
                case "3" :
                    pc.feed(lm.getCurrentUser().getPet());
                    break;
                case "4" :
                    pc.talk(lm.getCurrentUser().getPet());
                    break;
                case "5" :
                    pc.seeAge(lm.getCurrentUser().getPet());
                    break;
                case "6" :
                    pc.seeHealth(lm.getCurrentUser().getPet());
                    break;
                case "7" :
                    pc.feedMeds(lm.getCurrentUser().getPet());
                    break;
                case "8" :
                    checkDeath(lm.getCurrentUser().getPet());
                    pc.die(lm.getCurrentUser().getPet());
                    break;
                case "9" :
                    updateGraveyard(lm.getCurrentUser().getPet());
                    pc.bury(lm.getCurrentUser().getPet(), lm.getCurrentUser());
                    break;
                case "10" :
                    showGraveyard();
                    break;
                case "11" :
                    running = false;
                    break;
                default :
                    System.out.println("Oops! Please select a valid option.");
                    break;
            }
        }}

    /**
     * Creates a new pet. Ensures the User doesn't already have a pet or that the pet's name
     * isn't already taken.
     * @throws IOException buffered reader exception
     */
    private void newPet() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean running = true;
        while(running){
            System.out.println("Name your new pet!");
            String name = br.readLine();
            if (pc.createEgg(name, lm.getCurrentUser())){
                running = false;
            }
            else {
                System.out.println("That name is not available or you already have a pet.");
                running = false;
            }
        }
    }

    /**
     * Displays text of the user visiting their graveyard.
     * Text changes depending on whether there have been deaths or not
     */
    private void showGraveyard() {
        System.out.println("You visit the graveyard");
        if (lm.getCurrentUser().isDeaths()){
            System.out.println("Here are your deceased pets:");
            List<String> gr = lm.getCurrentUser().getGraveyard();
            for (String s: gr) {
                pc.showGraveyard(s);
            }
        }
        else {
            System.out.println("There have been no deaths so far!");
        }
    }

    /**
     * Checks whether the pet is dead and updates the user's graveyard
     * @param name String
     */
    private void updateGraveyard(String name) {
        if (!pc.isAlive(name) && name != null) {
            lm.getCurrentUser().updateGraveyard(name);
        }
    }

    /**
     * Checks whether a pet is able to die. Adds a point to the user's death count.
     * @param name String
     */
    private void checkDeath(String name){
        if (name != null && pc.isAlive(name)) {
            am.addDeathCount(lm.getCurrentUser().getUsername());
        }
    }


}
