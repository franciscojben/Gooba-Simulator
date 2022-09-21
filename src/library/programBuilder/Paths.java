package library.programBuilder;

/**
 * This interface ensures that the path to the databases are in a String format so that it can
 * be read by other classes.
 *
 * @author group_0236
 */
public interface Paths {
    /**
     * Get the path to the user DB.
     * @return String
     */
    String getUserPath();

    /**
     * Get the path to the pet DB.
     * @return String
     */
    String getPetPath();
}
