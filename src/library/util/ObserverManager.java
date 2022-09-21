package library.util;

import library.modules.loginSystem.loginEntities.User;
import library.modules.loginSystem.loginEntities.IUserBase;
import library.modules.loginSystem.loginEntities.UpdateEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ObserverManager implements IObserverManager {
    private final UpdateEvent uu;
    private final PropertyChangeSupport observable;
    /**
     * Constructor for ObserverManager
     * @param ub User base Interface
     */
    public ObserverManager(IUserBase ub) {
        this.uu = new UpdateEvent(ub);
        this.observable = new PropertyChangeSupport(this);
    }
    /**
     * add an observer
     * @param observer Observer to be added
     */
    public void addObserver(PropertyChangeListener observer){
        observable.addPropertyChangeListener(observer);
    }

    /**
     * notify an observer of a new event
     * @param newEvent Event to be reported
     */
    public void notifyObservers(PropertyChangeEvent newEvent){
        for (PropertyChangeListener observer : observable.getPropertyChangeListeners()){
            observer.propertyChange(newEvent);
        }
    }

    /**
     * Notify an observer to update after a property change event, to be called every time User is updated
     */
    public void updateUsers(){
        // Naive implementation
        PropertyChangeEvent p = uu.newEvent();
        notifyObservers(p);
    }
    /**
     * Notify an observer of a Login event
     */
    public void loginEvent(User user){
        PropertyChangeEvent p = new PropertyChangeEvent(user, "login", null, user);
        notifyObservers(p);
    }
    /**
     * Notify an observer of a Logout event
     */
    public void logoutEvent(User user){
        PropertyChangeEvent p = new PropertyChangeEvent(user, "logout", user, null);
        notifyObservers(p);
    }
}
