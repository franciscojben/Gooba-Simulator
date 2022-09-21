package library.modules.tradeSystem.tradeEntities;


import library.modules.loginSystem.loginEntities.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * A trade request
 */
public class TradeRequest implements Serializable {
    private final User sender;
    private final User receiver;
    private final PropertyChangeSupport observable;

    /**
     * Create an instance of Trade Request
     * @param sender sender of the trade
     * @param receiver receiver of the trade
     */
    public TradeRequest(User sender, User receiver){
        this.sender = sender;
        this.receiver = receiver;
        this.observable = new PropertyChangeSupport(this);
        this.addObserver(sender.getAccountData());
        this.addObserver(receiver.getAccountData());
        this.add();
    }

    /**
     * @return sender of this trade
     */
    public User getSender(){return this.sender;}

    /**
     * @return receiver of this trade
     */
    public User getReceiver(){return this.receiver;}

    private void add(){
        PropertyChangeEvent addTradeEvent = new PropertyChangeEvent(this, "addTrade", this, this);
        this.notifyObservers(addTradeEvent);
    }

    /**
     * Cancel this trade request
     */
    public void cancel(){
        PropertyChangeEvent cancelEvent = new PropertyChangeEvent(this, "cancelTrade", this, this);
        this.notifyObservers(cancelEvent);
    }

    /**
     * @return Summary of trade request
     */
    @Override
    public String toString(){
        return this.getSender().getUsername() + " would like to trade pets with " + this.getReceiver().getUsername();
    }
    /**
     * Adds observer
     * @param observer observer
     */
    public void addObserver(PropertyChangeListener observer){
        observable.addPropertyChangeListener(observer);
    }

    /**
     * Observable method that notifies observers of a new event.
     * @param newEvent new event
     */
    public void notifyObservers(PropertyChangeEvent newEvent){
        for (PropertyChangeListener observer : observable.getPropertyChangeListeners()){
            observer.propertyChange(newEvent);
        }
    }
}
