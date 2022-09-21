package usecases;


import library.modules.loginSystem.loginEntities.IUserBase;
import library.modules.loginSystem.loginEntities.RegUser;
import library.modules.loginSystem.loginEntities.User;
import library.modules.loginSystem.loginEntities.UserBase;
import library.modules.loginSystem.loginUseCases.AccountManager;
import library.modules.petSystem.petEntities.Gooba;
import library.modules.petSystem.petEntities.Pet;
import library.modules.petSystem.petUseCases.PetManager;
import library.modules.petSystem.petUseCases.TimerManager;
import library.modules.tradeSystem.tradeUseCases.TradeManager;
import library.presenters.ConsolePresenter;
import library.presenters.IPresenter;
import library.util.IObserverManager;
import library.util.ObserverManager;
import org.junit.*;

import java.util.HashMap;

import static org.junit.Assert.*;

public class TradeManagerTest {

    @Test
    public void TestSendTrade(){
        // Test setup
        IUserBase ub = new UserBase();
        User user1 = new RegUser("user1", "password");
        User user2 = new RegUser("user2", "password");
        ub.addUser(user1);
        ub.addUser(user2);
        IPresenter presenter = new ConsolePresenter();
        IObserverManager obMan = new ObserverManager(ub);
        PetManager pm = new PetManager(new AccountManager(ub, obMan, presenter), ub, new TimerManager(), presenter);
        Gooba pet1 = new Gooba(user1, "pet1");
        Gooba pet2 = new Gooba(user2, "pet2");
        HashMap<String, Pet> petBase = new HashMap<>();
        petBase.put("pet1", pet1);
        petBase.put("pet2", pet2);
        pm.createPetBase(petBase);
        TradeManager tm = new TradeManager(presenter, pm, obMan);
        user1.setPet("pet1");
        user2.setPet("pet2");

        tm.sendTrade(user1, user2);

        assertEquals(1, user1.getTradeRequests().size());
        assertEquals(1, user2.getTradeRequests().size());
        assertEquals(user1, user2.getTradeRequests().get(0).getSender());
        assertEquals(user2, user1.getTradeRequests().get(0).getReceiver());
    }

    @Test
    public void TestAcceptTrade(){
        // Test setup
        IUserBase ub = new UserBase();
        User user1 = new RegUser("user1", "password");
        User user2 = new RegUser("user2", "password");
        ub.addUser(user1);
        ub.addUser(user2);
        IPresenter presenter = new ConsolePresenter();
        IObserverManager obMan = new ObserverManager(ub);
        PetManager pm = new PetManager(new AccountManager(ub, obMan, presenter), ub, new TimerManager(), presenter);
        Gooba pet1 = new Gooba(user1, "pet1");
        Gooba pet2 = new Gooba(user2, "pet2");
        HashMap<String, Pet> petBase = new HashMap<>();
        petBase.put("pet1", pet1);
        petBase.put("pet2", pet2);
        pm.createPetBase(petBase);
        TradeManager tm = new TradeManager(presenter, pm, obMan);
        user1.setPet("pet1");
        user2.setPet("pet2");

        tm.sendTrade(user1, user2);
        tm.acceptTrade(0, user2);

        assertEquals("pet2", user1.getPet());
        assertEquals("pet1", user2.getPet());
        assertEquals(0, user1.getTradeRequests().size());
        assertEquals(0, user2.getTradeRequests().size());
    }

    @Test
    public void TestCancelTrade(){
        // Test setup
        IUserBase ub = new UserBase();
        User user1 = new RegUser("user1", "password");
        User user2 = new RegUser("user2", "password");
        ub.addUser(user1);
        ub.addUser(user2);
        IPresenter presenter = new ConsolePresenter();
        IObserverManager obMan = new ObserverManager(ub);
        PetManager pm = new PetManager(new AccountManager(ub, obMan, presenter), ub, new TimerManager(), presenter);
        Gooba pet1 = new Gooba(user1, "pet1");
        Gooba pet2 = new Gooba(user2, "pet2");
        HashMap<String, Pet> petBase = new HashMap<>();
        petBase.put("pet1", pet1);
        petBase.put("pet2", pet2);
        pm.createPetBase(petBase);
        TradeManager tm = new TradeManager(presenter, pm, obMan);
        user1.setPet("pet1");
        user2.setPet("pet2");

        tm.sendTrade(user1, user2);
        tm.cancelTrade(0, user2);
        assertEquals("pet1", user1.getPet());
        assertEquals("pet2", user2.getPet());
        assertEquals(0, user1.getTradeRequests().size());
        assertEquals(0, user2.getTradeRequests().size());
    }
}
