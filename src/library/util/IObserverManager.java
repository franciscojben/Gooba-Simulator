package library.util;

import library.modules.loginSystem.loginEntities.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This is a user action wherein includes all the methods required
 */
public interface IObserverManager {
    /**
     * Add an observer
     * @param observer PropertyChangeListener
     */
    void addObserver(PropertyChangeListener observer);

    /**
     * Notifies on new events
     * @param newEvent PropertyChangeListener
     */
    void notifyObservers(PropertyChangeEvent newEvent);

    /**
     * Used to update pets
     */
    void updateUsers();

    /**
     *
     * @param user User
     */
    void loginEvent(User user);
    void logoutEvent(User user);
}
