package entities;
import library.modules.loginSystem.loginEntities.User;
import org.junit.*;
import static org.junit.Assert.*;

public class UserTest {

    @Test(timeout = 10000)
    public void setup() {
        User u = new User("Umared", "password") {};
        assertEquals("Umared", u.getUsername());
    }
}
