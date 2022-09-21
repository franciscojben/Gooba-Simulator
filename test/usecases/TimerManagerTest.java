package usecases;
import library.modules.loginSystem.loginEntities.IUserBase;
import library.modules.loginSystem.loginEntities.RegUser;
import library.modules.loginSystem.loginEntities.User;
import library.modules.loginSystem.loginEntities.UserBase;
import library.modules.loginSystem.loginUseCases.AccountManager;
import library.modules.petSystem.petEntities.Egg;
import library.modules.petSystem.petEntities.Pet;
import library.modules.petSystem.petUseCases.PetManager;
import library.modules.petSystem.petUseCases.TimerManager;
import library.presenters.ConsolePresenter;
import library.presenters.IPresenter;
import library.util.IObserverManager;
import library.util.ObserverManager;
import org.junit.*;

import java.util.HashMap;

import static org.junit.Assert.*;

public class TimerManagerTest {
    @Test
    public void TestStopHatchTimer() throws InterruptedException {
        TimerManager tm = new TimerManager();
        IUserBase ub = new UserBase();
        User user1 = new RegUser("user1", "password");
        ub.addUser(user1);
        IPresenter presenter = new ConsolePresenter();
        IObserverManager obMan = new ObserverManager(ub);
        PetManager pm = new PetManager(new AccountManager(ub, obMan, presenter), ub, new TimerManager(), presenter);

        Egg testEgg = new Egg("testEgg", user1);
        HashMap<String, Pet> petBase = new HashMap<>();
        petBase.put("pet1", testEgg);
        user1.setPet("pet1");
        pm.createPetBase(petBase);

        tm.beginHatchTimer(10000, testEgg);
        Thread.sleep(1000);
        tm.stopHatchTimer();

        assertEquals("pet1", user1.getPet());
        assertTrue(pm.getPet("pet1") instanceof Egg);
        assertEquals(pm.getPet("pet1").getOwner(), user1);
    }
    @Test
    public void TestStopLifeTimer() throws InterruptedException {
        TimerManager tm = new TimerManager();
        IUserBase ub = new UserBase();
        User user1 = new RegUser("user1", "password");
        ub.addUser(user1);
        IPresenter presenter = new ConsolePresenter();
        IObserverManager obMan = new ObserverManager(ub);
        PetManager pm = new PetManager(new AccountManager(ub, obMan, presenter), ub, new TimerManager(), presenter);

        Egg testEgg = new Egg("testEgg", user1);
        HashMap<String, Pet> petBase = new HashMap<>();
        petBase.put("pet1", testEgg);
        user1.setPet("pet1");
        pm.createPetBase(petBase);

        tm.beginHatchTimer(10000, testEgg);
        Thread.sleep(1000);
        tm.stopHatchTimer();

        assertEquals("pet1", user1.getPet());
        assertTrue(pm.getPet("pet1") instanceof Egg);
        assertEquals(pm.getPet("pet1").getOwner(), user1);
    }
}
