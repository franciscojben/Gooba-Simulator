package library.programBuilder;

import library.consoleControllers.MainController;

public class Director {
    /**
     * Creates an instance of director.
     */
    public Director() {}

    /**
     * Denotes how the builder is to construct the program.
     * @param builder Builder
     */
    public MainController construct(Builder builder) {
        builder.buildSystem();
        builder.buildPresenter();
        builder.buildManager();
        builder.buildController();
        return builder.getProgram();
    }
}
