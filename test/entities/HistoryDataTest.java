package entities;
import library.modules.loginSystem.loginEntities.HistoryData;
import library.modules.loginSystem.loginEntities.AccountData;
import org.junit.*;
import static org.junit.Assert.*;

public class HistoryDataTest {
    @Test(timeout = 10000)
    public void TestAddHistory() {
        HistoryData ad1 = new HistoryData();
        AccountData ad2 = new AccountData();
        assertEquals("no History added", 1, ad1.getHistory().size(), 0.01);
        assertEquals("incorrect accountID", 0, ad2.getAccountID(), 0.01);
        ad2.setAccountNum(0);
    }

}
