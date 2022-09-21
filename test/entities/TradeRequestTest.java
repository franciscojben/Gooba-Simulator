package entities;

import org.junit.*;
import static org.junit.Assert.*;
import library.modules.loginSystem.loginEntities.RegUser;
import library.modules.loginSystem.loginEntities.User;
import library.modules.loginSystem.loginEntities.UserBase;
import library.modules.tradeSystem.tradeEntities.TradeRequest;
import library.util.ObserverManager;


public class TradeRequestTest {
    @Test
    public void TestCancel(){
        User user1 = new RegUser("user1", "password");
        User user2 = new RegUser("user2", "password");
        UserBase ub = new UserBase();
        ub.addUser(user1);
        ub.addUser(user2);
        ObserverManager obMan = new ObserverManager(ub);
        TradeRequest tr = new TradeRequest(user1, user2);

        assertEquals(tr, user1.getTradeRequests().get(0));
        assertEquals(tr, user2.getTradeRequests().get(0));
        tr.cancel();
        assertEquals(0, user1.getTradeRequests().size());
        assertEquals(0, user2.getTradeRequests().size());
    }
}
