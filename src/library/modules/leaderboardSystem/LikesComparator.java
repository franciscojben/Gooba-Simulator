package library.modules.leaderboardSystem;

import library.modules.loginSystem.loginEntities.User;

/**
 * A comparator class used for the leaderboard
 * @author group_0236
 */
public class LikesComparator implements IAttributeComparator<User>{
    /**
     *Return the stat being compared
     * @return String
     */
    @Override
    public String getStat() {
        return "Likes";
    }

    /**
     * Overridden compare class from Comparator. Return a negative int if one's number of likes is less than two's, 0 if
     * the values are equal, and a positive integer if one's number of likes is greater than two's.
     * @param one the first object to be compared.
     * @param two the second object to be compared.
     * @return int as described above.
     */
    @Override
    public int compare(User one, User two) {
        return Integer.compare(one.getAccountData().getNumLikes(), two.getAccountData().getNumLikes());
    }
}
