package library.consoleControllers;

import java.io.IOException;

/**
 * The interface that denotes which is the main tradeControllers of the program.
 *
 * @author group_0236
 */
public interface MainController {
    /**
     * The main tradeControllers must have the ability to run the program.
     * @throws IOException buffered reader exception
     */
    void run() throws IOException;
}
