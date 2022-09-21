package library.programBuilder;

import java.io.File;

/**
 * This class is responsible for building the system and database components of the program.
 *
 * @author group_0236
 */
public class SystemComponent implements Paths {
    private final String userDB;
    private final String petDB;

    /**
     * Create an instance of SystemComponent.
     */
    public SystemComponent() {
        File UserDB = new File("src/data/userDB.ser");
        File PetDB = new File("src/data/petDB.ser");
        this.userDB = UserDB.getAbsolutePath();
        this.petDB = PetDB.getAbsolutePath();
    }

    /**
     * Get the user database path.
     * @return String
     */
    public String getUserPath() {
        return this.userDB;
    }

    /**
     * Get the pet database path.
     * @return String
     */
    public String getPetPath() {
        return this.petDB;
    }
}
