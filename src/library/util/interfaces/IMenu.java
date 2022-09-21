package library.util.interfaces;

import java.io.IOException;

/** The Menu interface ensures that whatever class that implements it has a menu.
 *  This is reserved primarily for the loginControllers in the system.
 *
 *  @author group_0236
 */
public interface IMenu {
    /**
     * Create the menu for user's to control the program.
     * @throws IOException Input and output error
     */
    void menu() throws IOException;
}
