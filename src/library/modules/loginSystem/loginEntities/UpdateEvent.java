package library.modules.loginSystem.loginEntities;

import java.beans.PropertyChangeEvent;

/**
 * Responsible for updating users and sending requests.
 *
 * @author group_0236
 */
public class UpdateEvent {
    final IUserBase ub;

    public UpdateEvent(IUserBase ub){
        this.ub = ub;
    }

    // This update method should be called whenever pets are updated
    public PropertyChangeEvent newEvent(){
        // Naive implementation
        Object users = ub.getSystem();
        return new PropertyChangeEvent(users, "userUpdate", users, users);
    }
}
