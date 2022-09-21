package entities;
import static org.junit.Assert.*;

import library.modules.leaderboardSystem.DeathComparator;
import library.modules.leaderboardSystem.leaderboardEntities.Leaderboard;
import library.modules.loginSystem.loginEntities.IUserBase;
import library.modules.loginSystem.loginEntities.RegUser;
import library.modules.loginSystem.loginEntities.User;
import library.modules.loginSystem.loginEntities.UserBase;
import org.junit.*;

public class LeaderboardTest {
    @Test
    public void TestInit(){
        User user1 = new RegUser("user1", "password");
        User user2 = new RegUser("user2", "password");
        IUserBase ub = new UserBase();
        ub.addUser(user1);
        ub.addUser(user2);
        user1.addDeathCount();
        user1.addDeathCount();
        user2.addDeathCount();
        DeathComparator comparator = new DeathComparator();

        Leaderboard deathLb = new Leaderboard<>(2, comparator, ub);
        assertEquals(2, deathLb.getLbList().size());
        assertEquals(user2, deathLb.getLbList().get(1));
        assertEquals(user1, deathLb.getLbList().get(0));
    }

}
