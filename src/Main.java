import library.consoleControllers.MainController;
import library.programBuilder.Director;
import library.programBuilder.Builder;
import library.programBuilder.ProgramBuilder;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Director director = new Director();
        Builder builder = new ProgramBuilder();
        MainController console = director.construct(builder);
        console.run();
    }
}