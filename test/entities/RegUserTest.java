package entities;

import library.modules.loginSystem.loginEntities.RegUser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegUserTest {
    @Test(timeout = 10000)
    public void setup() {
        RegUser r = new RegUser("Umared", "password") {};
        assertEquals("password", r.getPassword());
    }
}

