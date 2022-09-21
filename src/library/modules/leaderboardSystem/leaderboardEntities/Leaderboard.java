package library.modules.leaderboardSystem.leaderboardEntities;

import library.modules.leaderboardSystem.IAttributeComparator;
import library.modules.loginSystem.loginEntities.IUserBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Leaderboard is an entity that stores the top scores for a specified attribute
 *
 * @author group_0236
 */
public class Leaderboard <User> implements ILeaderboard<User> {
    private final int lbLength;
    /**
     * Index 0 contains the object with the lowest score.
     * Index -1 is #1.
     */
    private List<User> lbList = new ArrayList<>();
    private final IAttributeComparator<User> lbComparator;
    private final IUserBase ub;

    /**
     * Creates an instance of a Leaderboard
     * @param lbLength int
     * @param lbComparator IAttributeComparator</T>
     * @param ub IUserBase
     */
    public Leaderboard (int lbLength, IAttributeComparator<User> lbComparator, IUserBase ub) {
        this.lbLength = lbLength;
        this.lbComparator = lbComparator;
        this.ub = ub;
        initialize();
    }

    /**
     * Initializes a leaderboard
     */
    public void initialize(){
        @SuppressWarnings("unchecked")
        List<User> userBaseList = (List<User>) ub.getAllUsers();
        userBaseList.sort(lbComparator);
        int upperBound = Math.min(userBaseList.size(), lbLength);
        this.lbList = userBaseList.subList(0, upperBound);
    }

    /**
     * Return the leaderboard list
     * @return ArrayList</T>
     */
    @Override
    public List<User> getLbList() {
        return lbList;
    }




}
