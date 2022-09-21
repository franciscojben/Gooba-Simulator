package library.modules.leaderboardSystem;


import java.util.Comparator;

/**
 * This Interface is meant for Comparator classes that specify what Object and variable is being compared, in addition
 * to a String representation for methods that require PropertyChangeEvent. For example, a class that compares the age
 * of Pets would override the compare method accordingly.
 * @param <User>
 *
 * @author group_0236
 */
public interface IAttributeComparator<User> extends Comparator<User> {
    /**
     * Return the stat that that can be compared as a String.
     * @return String
     */
    @SuppressWarnings("SameReturnValue")
    String getStat();

    /**
     * Compare two users
     * @param one the first object to be compared.
     * @param two the second object to be compared.
     * @return int
     */
    int compare(User one, User two);

}
