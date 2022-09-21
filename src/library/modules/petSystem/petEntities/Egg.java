package library.modules.petSystem.petEntities;

import library.modules.loginSystem.loginEntities.User;
import library.modules.petSystem.PetFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Responsible for creating an egg entity
 *
 * @author paulo
 */
public class Egg extends Pet {
    private final LocalDateTime hatchTime;
    private final PropertyChangeSupport observable;

    /**
     * Egg constructor for new egg
     * @param name pet name
     * @param owner egg owner
     */
    public Egg(String name, User owner){
        super(owner, name);
        this.hatchTime = LocalDateTime.now().plusMinutes(1);
        this.observable = new PropertyChangeSupport(this);
    }


    /**
     * Egg constructor for reading from db
     * @param name pet name
     * @param hatchTime time it takes to hatch
     * @param owner egg owner
     */
    public Egg(String name, LocalDateTime hatchTime, User owner){
        super(owner, name);
        this.hatchTime = hatchTime;
        this.observable = new PropertyChangeSupport(this);
    }

    /**
     * Creates a pet of random type
     */
    public void hatch(){
        PetFactory factory = new PetFactory();
        int random = ThreadLocalRandom.current().nextInt(1, 4 + 1);
        Pet pet = factory.makePet(random, this.owner, this.getName());
        PropertyChangeEvent newEvent = new PropertyChangeEvent(this, "hatched", this.getName(), pet);
        notifyObservers(newEvent);
    }

    /**
     * Set the owner to owner
     * @param owner egg owner
     */
    public void setOwner(User owner){this.owner = owner;}

    /**
     * @return Egg response for this action
     */
    @Override
    public String playWith() {
        return "The egg jiggles a bit...";
    }

    /**
     * @return Egg response for this action
     */
    @Override
    public String feed() {
        return "The egg jiggles a bit...";
    }

    /**
     * @return Egg response for this action
     */
    @Override
    public String heal() {
        return "The egg jiggles a bit...";
    }

    /**
     * @return Egg response for this action
     */
    @Override
    public String talk() {
        return "The egg jiggles a bit...";
    }

    /**
     * @return Egg response for this action
     */
    @Override
    public String die() {
        return "The egg jiggles a bit...";
    }

    /**
     * Return the owner of this egg
     */
    public User getOwner(){return this.owner;}

    /**
     * @return Type of pet
     */
    @Override
    public String getPetType() { return "Egg";}

    /**
     * Add an observer
     * @param observer observer class
     */
    public void addObserver(PropertyChangeListener observer){
        observable.addPropertyChangeListener(observer);
    }

    /**
         * Get the egg hatch time
         */
    public LocalDateTime getHatchTime(){return this.hatchTime;}

    /**
     * Notify observers of an event
     * @param newEvent PropertyChangeEvent
     */
    public void notifyObservers(PropertyChangeEvent newEvent){
        for (PropertyChangeListener observer : observable.getPropertyChangeListeners()){
            observer.propertyChange(newEvent);
        }
    }
}
