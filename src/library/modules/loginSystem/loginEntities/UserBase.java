package library.modules.loginSystem.loginEntities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserBase implements IUserBase {
    private HashMap<String, User> users;

    /**
     * Construct the UserBase object.
     */
    public UserBase() {
        this.users = new HashMap<>();
        User superUser = new AdminUser("ADMIN", "password");
        superUser.setPet("demoGooba");
        this.users.put("ADMIN", superUser);
    }
    /**
     * Create the user system from the database.
     * @param object Object
     */
    @SuppressWarnings("unchecked")
    public void createUserBase(Object object){
        if ((object instanceof HashMap)){
            this.users = (HashMap<String, User>) object;
        }
    }
    /**
     * Get the User object from a username.
     * @param username String
     * @return User
     */
    public User getUser(String username){
        return users.get(username);
    }

    /**
     * Check if there is a User with a given username.
     * @param username String
     * @return boolean
     */
    public boolean isUser(String username){
        return users.containsKey(username);
    }

    /**
     * Add a user to the user base.
     * @param user User
     */
    public void addUser(User user){
        users.put(user.getUsername(), user);
    }

    /**
     * Remove a user from the user base.
     */
    public void removeUser(User user){
        users.remove(user.getUsername());
    }

    /**
     * Get all users in the system.
     * @return List[User]
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    /**
     * Return the hashmap user system.
     * @return HashMap[String, User]
     */
    public HashMap<String, User> getSystem() {
        return users;
    }
}
