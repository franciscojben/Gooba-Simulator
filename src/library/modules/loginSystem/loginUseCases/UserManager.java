package library.modules.loginSystem.loginUseCases;

import library.modules.loginSystem.loginEntities.AdminUser;
import library.modules.loginSystem.loginEntities.IUserBase;
import library.modules.loginSystem.loginEntities.RegUser;
import library.modules.loginSystem.loginEntities.User;
import library.util.IObserverManager;
import library.util.interfaces.IManager;
import library.presenters.presenterInterfaces.IUserManager;

import java.util.List;

public class UserManager implements IManager {
    private final IUserBase userBase;
    private final IObserverManager obMan;
    private final IUserManager presenter;

    public UserManager(IUserBase userBase, IObserverManager obMan, IUserManager presenter) {
        this.userBase = userBase;
        this.obMan = obMan;
        this.presenter = presenter;
    }

    public boolean createRegUser(String username, String password){
        String response;
        if(userBase.isUser(username)){
            response = "Username is taken";
        }else{
            response = "User successfully created";
            User user = new RegUser(username, password);
            userBase.addUser(user);
            obMan.updateUsers();
        }
        presenter.responseUser(response);

        return response.equals("User successfully created");
    }
    public boolean createAdminUser(String username, String password, String currentUser){
        String response;
        if(!userBase.getUser(currentUser).getAdminStatus()){
            response = "User does not have permission to create Admin users.";
        } else if(userBase.isUser(username)){
            response = "Username is taken";
        }else{
            response = "User successfully created";
        }
        presenter.responseUser(response);
        User user = new AdminUser(username, password);
        userBase.addUser(user);
        obMan.updateUsers();
        return response.equals("User successfully created");
    }

    public boolean deleteUser(String username, String currentUser){
        String response;
        if (!userBase.getUser(currentUser).getAdminStatus()) {
            response = "Operation failed. User not authorized to delete.";
        } else if (!userBase.isUser(username)) {
            response = "Operation failed. User does not exist.";
        } else if (userBase.getUser(username).getAdminStatus()) {
            response = "Operation failed. Cannot delete admin.";
        } else {
            userBase.removeUser(userBase.getUser(username));
            obMan.updateUsers();
            response = "User has been deleted.";
        }
        presenter.responseUser(response);
        return response.equals("User has been deleted.");
    }

    public boolean banUser(String username, String currentUser){
        String response;
        if (!userBase.getUser(currentUser).getAdminStatus()){
            response = "Operation failed. User not authorized to ban.";
        }else if(!userBase.isUser(username)){
            response = "User Doesn't Exists.";
        }else if(userBase.getUser(username).getClass() == AdminUser.class){
            response = "Operation failed. Cannot ban admin.";
        }else if(userBase.getUser(username).getBanStatus()){
            response = "Operation failed. User is already banned.";
        }else{
            userBase.getUser(username).setBanStatus(true);
            obMan.updateUsers();
            response = "User successfully banned.";
        }
        presenter.responseUser(response);
        return response.equals("User successfully banned.");
    }

    public boolean verifyUser(String username, String password){
        String response;

        if (!userBase.isUser(username)){
            response = "Login failed. User not found.";
        } else if (!userBase.getUser(username).getPassword().equals(password)) {
            response = "Login failed. Invalid username or password";
        }else if (userBase.getUser(username).getBanStatus()) {
            response = "Login failed. User is banned.";
        }else{
            response = "Login successful.";
        }

        presenter.responseLogin(response);
        return response.equals("Login successful.");
    }

    public User getUser(String username){
        return this.userBase.getUser(username);
    }
    /**
     * Prepare the data for reading from a database.
     * @return String
     */
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        List<User> userList = userBase.getAllUsers();
        for(User user : userList) {
            result.append(user.toString()).append("\n");
        }
        return result.toString();
    }

    @Override
    public Object getData() {
        return this.userBase.getSystem();
    }
}
