package library.programBuilder;

import library.consoleControllers.MainController;

/**
 * This interface denotes the methods needed to build the program.
 *
 * @author group_0236
 */
public interface Builder {
    /**
     * Build the database and system related aspects of the program.
     */
    void buildSystem();

    /**
     * Build the manager(s) of the program.
     */
    void buildManager();

    /**
     * Build the presenter(s) of the program.
     */
    void buildPresenter();

    /**
     * Build the tradeControllers(s) of the program.
     */
    void buildController();

    /**
     * Get the product: the main tradeControllers of the program.
     * @return MainController
     */
    MainController getProgram();
}
