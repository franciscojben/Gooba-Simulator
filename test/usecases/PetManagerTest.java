package usecases;
import static org.junit.Assert.*;
import org.junit.*;
import library.modules.loginSystem.loginEntities.IUserBase;
import library.modules.loginSystem.loginEntities.RegUser;
import library.modules.loginSystem.loginEntities.User;
import library.modules.loginSystem.loginEntities.UserBase;
import library.modules.loginSystem.loginUseCases.AccountManager;
import library.modules.petSystem.petEntities.Gooba;
import library.modules.petSystem.petEntities.Pet;
import library.modules.petSystem.petUseCases.PetManager;
import library.modules.petSystem.petUseCases.TimerManager;
import library.presenters.ConsolePresenter;
import library.presenters.IPresenter;
import library.util.IObserverManager;
import library.util.ObserverManager;

import java.util.HashMap;

public class PetManagerTest {
    @Test
    public void TestCreateEgg(){
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
        HashMap<String, Pet> petBase = new HashMap<>();
        petBase.put("pet1", pet1);
        pm.createPetBase(petBase);

        pm.createEgg("TestEgg1", user1);
        pm.createEgg("TestEgg2", user2);

        assertEquals(pet1, pm.getPet("pet1"));
        assertEquals("TestEgg2", user2.getPet());
    }

    @Test
    public void TestSwapPets(){
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
        user1.setPet("pet1");
        user2.setPet("pet2");
        HashMap<String, Pet> petBase = new HashMap<>();
        petBase.put("pet1", pet1);
        petBase.put("pet2", pet2);
        pm.createPetBase(petBase);

        pm.swapPets("user1", "user2");

        assertEquals("pet2", user1.getPet());
        assertEquals("pet1", user2.getPet());
        assertEquals(user2, pet1.getOwner());
        assertEquals(user1, pet2.getOwner());
    }
}
