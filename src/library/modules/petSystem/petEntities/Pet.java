package library.modules.petSystem.petEntities;

import library.modules.loginSystem.loginEntities.User;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * Responsible for creating an instance of pet.
 *
 * @author paulo, Umared44, sanotoast
 */
public abstract class Pet implements Serializable{
    protected User owner;
    protected final PetData data;

    /**
     * Constructor for new Pet
     * @param owner pet owner
     * @param name pet name
     */
    public Pet(User owner, String name) {
        this.data = new PetData(name, true, true, 0, 0, 20, 100, LocalDateTime.now().toString());
        this.owner = owner;
    }

    /**
     * Constructor for reading from db
     * @param name pet name
     * @param owner pet owner
     * @param lifeStatus pet life status
     * @param healthStatus pet health status
     * @param hungerStatus pet hunger status
     * @param age pet age
     * @param weight pet weight
     * @param happiness pet happiness
     * @param birthday pet birthday
     */
    public Pet(String name, User owner, boolean lifeStatus, boolean healthStatus, int hungerStatus, int age, int weight, int happiness, String birthday){
        this.data = new PetData(name, lifeStatus, healthStatus, hungerStatus, age, weight, happiness, birthday);
        this.owner = owner;
    }

    /**
     * @return owner of this pet
     */
    public User getOwner(){return this.owner;}

    /**
     * Set owner to newOwner
     * @param newOwner new owner of pet
     */
    public void setOwner(User newOwner){this.owner = newOwner;}

    /**
     * @return String response to this action
     */
    public abstract String playWith();

    /**
     * @return String response to this action
     */
    public abstract String feed();

    /**
     * @return String catchphrase
     */
    public abstract String talk();

    /**
     * @return String Type of pet
     */
    public abstract String getPetType();

    /**
     * @return String response to this action
     */
    public abstract String die();
    /**
     * @return String response to healing the pet
     */
    public abstract String heal();
    /**
     * make the pet hungrier by 1 point
     */
    public void starve() {data.setHungerStatus(data.getHungerStatus() + 1);}
    /**
     * make the pet loose happiness by 1 point
     */
    public void sadden(){data.setHappiness(data.getHappiness()-1);}
    /**
     * make the pet get older by 1 day
     */
    public void getOlder() {data.setAge(data.getAge() + 1);}
    /**
     * @return the name of the pet
     */
    public String getName(){
        return this.data.getName();
    }
    /**
     * @return life status of pet
     */
    public boolean getLifeStatus(){ return this.data.getLifeStatus(); }
    /**
     * @return Hunger status of pet
     */
    public int getHungerStatus(){return this.data.getHungerStatus();}

    /**
     * @return happiness status of pet
     */
    public int getHappinessStatus(){return this.data.getHappiness();}
    /**
     * @return health status of pet
     */
    public boolean getHealthStatus(){return this.data.getHealthStatus();}
    /**
     * set health status of pet
     */
    public void setHealthStatus (Boolean health){this.data.setHealthStatus(health);}
    /**
     * @return age of pet
     */
    public int getAge(){ return this.data.getAge();}
}
