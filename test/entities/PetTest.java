package entities;
import static org.junit.Assert.*;

import library.modules.loginSystem.loginEntities.RegUser;
import library.modules.loginSystem.loginEntities.User;
import library.modules.petSystem.petEntities.Gooba;
import library.modules.petSystem.petEntities.Goza;
import library.modules.petSystem.petEntities.Kikitchi;
import library.modules.petSystem.petEntities.Pet;
import org.junit.*;

public class PetTest {

    @Test
    public void TestPlayWith(){
        User user1 = new RegUser("user1", "password");
        Pet pet = new Gooba("pet1", user1, true, true, 50, 0, 50, 90, "2020-01-01");

        pet.playWith();
        assertEquals(91, pet.getHappinessStatus());
    }

    @Test
    public void TestFeed(){
        User user1 = new RegUser("user1", "password");
        Pet pet = new Goza("pet1", user1, true, true, 50, 0, 50, 90, "2020-01-01");
        pet.feed();
        assertEquals(35, pet.getHungerStatus());
    }

    @Test
    public void TestDie(){
        User user1 = new RegUser("user1", "password");
        Pet pet = new Goza("pet1", user1, true, true, 50, 0, 50, 90, "2020-01-01");

        assertTrue(pet.getLifeStatus());
        pet.die();
        assertFalse(pet.getLifeStatus());
    }

    @Test
    public void TestStarve(){
        User user1 = new RegUser("user1", "password");
        Pet pet = new Kikitchi("pet1", user1, true, true, 50, 0, 50, 90, "2020-01-01");

        pet.starve();
        assertEquals(51, pet.getHungerStatus());
    }
}
