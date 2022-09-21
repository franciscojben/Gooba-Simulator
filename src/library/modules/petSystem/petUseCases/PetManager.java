package library.modules.petSystem.petUseCases;

import library.modules.petSystem.petEntities.Egg;
import library.modules.petSystem.petEntities.Gooba;
import library.modules.petSystem.petEntities.Pet;
import library.util.interfaces.IManager;
import library.modules.loginSystem.loginUseCases.AccountManager;
import library.modules.loginSystem.loginEntities.IUserBase;
import library.modules.loginSystem.loginEntities.User;
import library.presenters.presenterInterfaces.IPetManager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Objects;

/**
 * PetManager handles all methods related to pets and eggs.
 * The pets hashMap is the storage for all pets in the system.
 */
public class PetManager implements IManager, PropertyChangeListener {
    private HashMap<String, Pet> pets;
    private final IUserBase ub;
    private final AccountManager am;
    private final PropertyChangeSupport observable;
    private final TimerManager tm;
    private final IPetManager presenter;

    /**
     * Constructor for PetManager. Adds a default 'demoGooba' to each instance.
     * @param am AccountManager
     */
    public PetManager(AccountManager am, IUserBase ub, TimerManager tm, IPetManager presenter){
        this.pets = new HashMap<>();
        this.am = am;
        this.ub = ub;
        this.tm = tm;
        this.presenter = presenter;
        this.observable = new PropertyChangeSupport(this);
        Pet demoPet = new Gooba(ub.getUser("ADMIN"), "demoGooba");
        this.pets.put("demoGooba", demoPet);
    }


    /**
     * Set the pet base to an object of the pet base
     * @param object Object
     */
    @SuppressWarnings("unchecked")
    public void createPetBase(Object object){
        if ((object instanceof HashMap)){
            this.pets = (HashMap<String, Pet>) object;
        }
    }
    /**
     * Create an egg and set its owner to owner.
     * @param name  String
     * @param owner User
     * @return true if egg was created successfully.
     */
    public boolean createEgg(String name, User owner) {
        String response;
        boolean added = false;
        if(pets.containsKey(name)){
            response = "Operation failed. Pet name already exists";
        }else if(owner.getPet() == null || owner.getPet().equals("null")){
            response = "Successfully added new egg. Your egg will hatch in 1 minute.";
            Egg tempEgg = new Egg(name, owner);
            pets.put(name, tempEgg);
            tempEgg.addObserver(this);
            am.setPet(owner.getUsername(), name);
            tm.beginHatchTimer(60000, tempEgg);
            added = true;
        }else{
            response = "Operation failed. Can only have one pet at a time.";
        }
        presenter.responseAddPet(response);
        this.updatePets();
        return added;
    }

    /**
     * Kills the given pet
     * @param name String
     */
    public void die(String name) {
        String response = "Nothing interesting happens.";
        if (pets.containsKey(name)) {
            if (isAlive(name)) {
                response = this.getPet(name).die();
                this.updatePets();
            }else{
                response = name + " is already dead.";
            }
        }
        presenter.responseDie(response);

    }

    /**
     * Return life status
     * @param name pet name
     * @return if pet is alive
     */
    public boolean isAlive(String name) {
        if (pets.containsKey(name)) {
            return pets.get(name).getLifeStatus();
        }
        else {
            return false;
        }
    }

    /**
     * Bury this pet in the graveyard
     * @param name pet name
     * @param owner pet owner
     */
    public void bury(String name, User owner) {
        String response = "Nothing interesting happens.";
        if (pets.containsKey(name)) {
            if (!isAlive(name)) {
                owner.setPet(null);
                response = "You buried " + name + " in the graveyard.";
            }
            else {
                response = name + " is still alive!";
            }
        }
        presenter.responseBury(response);
    }

    /**
     * Feed medicine to the given pet
     * @param name String
     */
    public void feedMeds(String name) {
        String response = "Nothing interesting happens.";
        if (pets.containsKey(name)) {
            Pet pet = this.getPet(name);
            response = pet.heal();
            this.updatePets();
        }
        presenter.responseSeeHealth(response);
    }

    /**
     * See the age of the given pet
     * @param name String
     */
    public void seeAge(String name) {
        String response = name + " is " + this.getPet(name).getAge() + " days old.";
        presenter.responseSeeAge(response);
    }
    /**
     * See the health status of the given pet
     * @param name String
     */
    public void seeHealthStatus(String name) {
        String response = "Nothing interesting happens.";
        Pet pet = this.getPet(name);
        if (pet.getHealthStatus()) {
            response = name + " is healthy.";
        } else if (!(pet.getHealthStatus())) {
            response = name + " is not feeling well at all.";
        }
        presenter.responseSeeHealth(response);
    }

    /**
     * Play with the given pet
     * @param name String
     */
    public void playWith(String name){
        String response = "Nothing interesting happens.";
        if (pets.containsKey(name)) {
            Pet pet = this.getPet(name);
            response = pet.playWith();
            this.updatePets();
        }
        presenter.responsePlayWith(response);
    }

    /**
     * Feed the given pet
     * @param name String
     */
    public void feed(String name){
        String response = "Nothing interesting happens.";
        if (pets.containsKey(name)) {
            Pet pet = this.getPet(name);
            response = pet.feed();
            this.updatePets();
        }
        presenter.responseFeed(response);
    }

    /**
     * Talk with the given pet
     * @param name pet name
     */
    public void talk(String name) {
        String response = "You talk to yourself.";
        if (pets.containsKey(name)) {
            Pet pet = this.getPet(name);
            response = pet.talk();
        }
        presenter.responseTalk(response);
    }

    /**
     * @param name String
     * @return a pet based on its name
     */
    public Pet getPet(String name){
        return pets.get(name);
    }

    /**
     * Swaps the two users' pets.
     * @param currentUsername String
     * @param otherUsername   String
     * @return true if successful swap.
     */
    public boolean swapPets(String currentUsername, String otherUsername){
        User otherUser = ub.getUser(otherUsername);
        User currentUser = ub.getUser(currentUsername);
        String response;
        boolean success = false;
        if ((otherUser != null && currentUser != null)){
            String otherPet = otherUser.getPet();
            String currentPet = currentUser.getPet();
            if (currentPet == null) {
                otherUser.removePet();
            }
            else {
                otherUser.setPet(currentPet);
                this.getPet(currentPet).setOwner(otherUser);
            }
            if (otherPet == null){
                currentUser.removePet();
            }else{
                currentUser.setPet(otherPet);
                this.getPet(otherPet).setOwner(currentUser);
            }

            response = "Pets successfully swapped.";
            success = true;
        }else{
            response = "The user does not exist.";
        }
        presenter.responseSwap(response);
        this.updatePets();
        return success;
    }

    private void beginHatchTimer(User user){
        Object pet = this.getPet(user.getPet());
        Egg egg;
        if (pet instanceof Egg) {
            egg = (Egg)pet;
            LocalDateTime hatchTime = egg.getHatchTime();
            long delay = Math.max(ChronoUnit.MILLIS.between(LocalDateTime.now(), hatchTime), 0);
            tm.beginHatchTimer(delay, egg);
        }
    }
    private void beginLifeTimers(User user) {
        Pet pet = this.getPet(user.getPet());
        tm.beginAgeTimer(pet);
        tm.beginHappinessTimer(pet);
        tm.beginHungerTimer(pet);
    }
    private void stopLifeTimers(User user) {
        Pet pet = this.getPet(user.getPet());
        tm.stopLifeTimers();
        if (!(pet.getHealthStatus())){
            tm.stopSicknessTimer();
        }
    }

    private void stopHatchTimer(User user){
        Object pet = this.getPet(user.getPet());
        if (pet instanceof Egg) {
            tm.stopHatchTimer();
        }
    }

    public void showGraveyard(String graveyard){
        presenter.responseGraveyard(graveyard);
    }

    /**
     * @return a string representation of the Pets map.
     * Used for writing data to CSV.
     */
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for(Pet pet : pets.values()){
            result.append(pet.toString()).append("\n");
        }
        return result.toString();
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

    /**
     * This method should be called everytime the Pets map is updated. It notifies the observers (usually an ISystem)
     * that a change has been made and to update the data.
     */
    public void updatePets(){
        // Naive implementation
        PropertyChangeEvent newEvent = new PropertyChangeEvent(this.pets, "petsUpdate", this.pets, this.pets);
        notifyObservers(newEvent);
    }

    /**
     * Observer method that updates the Pets map with an egg that was just hatched.
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Objects.equals(evt.getPropertyName(), "hatched")){
            String name = (String) evt.getOldValue();
            Pet newPet = (Pet) evt.getNewValue();
            String response = (newPet.getName() + " has hatched into a " + newPet.getPetType() + "!");
            this.pets.replace(name, newPet);
            this.presenter.responseHatch(response); // How to get this through to the presenter instead?
            this.updatePets();
            this.beginHatchTimer(((Pet) evt.getNewValue()).getOwner()); // Starts the timer for the pet when it hatches
        }
        else if (Objects.equals(evt.getPropertyName(), "loginEvent")){
            this.beginHatchTimer((User) evt.getNewValue());
            this.beginLifeTimers((User) evt.getNewValue());
        } else if (Objects.equals(evt.getPropertyName(), "logoutEvent")){
            this.stopHatchTimer((User) evt.getOldValue());
            this.stopLifeTimers((User) evt.getNewValue());
        }
    }

    /**
     * @return the HashMap of pets
     */
    @Override
    public Object getData() {
        return this.pets;
    }
}
