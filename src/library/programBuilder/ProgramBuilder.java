package library.programBuilder;

import library.consoleControllers.MainConsole;
import library.consoleControllers.MainController;

public class ProgramBuilder implements Builder {
    private SystemComponent sys;
    private ManagerComponent man;
    private PresenterComponent pre;
    private ControllerComponent con;

    public void buildSystem() {
        this.sys = new SystemComponent();
    }

    public void buildManager() {
        this.man = new ManagerComponent(pre);
    }

    public void buildPresenter() {
        this.pre = new PresenterComponent();
    }

    public void buildController() {
        this.con = new ControllerComponent(sys, man);
    }

    public void generateBase() {
        new GenerateBase(sys, man, con);
    }

    public MainController getProgram() {
        generateBase();
        return new MainConsole(man, con);
    }
}
