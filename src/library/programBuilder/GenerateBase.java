package library.programBuilder;

import java.io.FileNotFoundException;

public class GenerateBase {

    /**
     * Create the user and pet bases.  Also gets the observers ready to track changes.
     *
     * @param sys SystemComponent
     * @param man ManagerComponent
     * @param con ControllerComponent
     */
    public GenerateBase(SystemComponent sys, ManagerComponent man, ControllerComponent con) {
        // Create user and pet bases
        try {
            man.getUb().createUserBase(con.getUserSA().readFromFile(sys.getUserPath()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        man.getObMan().addObserver(con.getUserSA());
        man.getObMan().addObserver(man.getPm());
        man.getObMan().addObserver(man.getLbm());

        try {
            man.getPm().createPetBase(con.getPetSA().readFromFile(sys.getPetPath()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        man.getPm().addObserver(con.getPetSA());
        man.getPm().addObserver(con.getPetSA());
    }
}
