package entities;
import org.junit.*;
import static org.junit.Assert.*;
import library.modules.loginSystem.loginEntities.AdminUser;
public class AdminUserTest {
    // Test AdminUser Constructor
    @Test(timeout = 10000)
    public void TestAdminUserConstructor() {
        AdminUser admin = new AdminUser("sneaky", "sneaky");
        assertEquals("sneaky", admin.getUsername());
        assertEquals("sneaky", admin.getPassword());
    }

    // Test AdminUser is an Admin
    @Test(timeout = 10000)
    public void TestAdminUserStatus() {
        AdminUser admin = new AdminUser("not", "admin");
        assertTrue(admin.getAdminStatus());
    }
}
