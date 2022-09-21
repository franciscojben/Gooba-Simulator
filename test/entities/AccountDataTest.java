package entities;
import library.modules.loginSystem.loginEntities.AccountData;
import org.junit.*;
import static org.junit.Assert.*;

public class AccountDataTest {
    // Test AccountData Constructor
    @Test(timeout = 10000)
    public void TestAccountDataConstructor() {
        AccountData ad = new AccountData();
        AccountData da;
        da = ad;
        assertSame(ad, da);
        ad.setAccountNum(0);
    }

    // Test AccountData Constructor assigning unique account ID
    @Test(timeout = 10000)
    public void TestAccountDataAssignID() {
        AccountData ad1 = new AccountData();
        AccountData ad2 = new AccountData();
        assertEquals("incorrect accountID", 0, ad1.getAccountID(), 0.01);
        assertEquals("incorrect accountID", 1, ad2.getAccountID(), 0.01);
        ad1.setAccountNum(0);
    }
    @Test(timeout = 10000)
    //Test AccountData constructor getting name
    public void TestGetName() {
        AccountData ad1 = new AccountData(0,"usr1", false, 0, ";");
        assertEquals(ad1.getName(), "usr1");
        AccountData ad2 = new AccountData(0,"usr2", false, 0, ";");
        assertEquals(ad2.getName(), "usr2");
    }




}
