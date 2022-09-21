package library.modules.petSystem.petEntities;

import library.util.interfaces.DataType;

import java.io.Serializable;
/**
 * Responsible for storing all pet data.
 *
 * @author sanotoast, Umared44
 */
public class PetData implements DataType, Serializable {
    private String name;
    protected boolean lifeStatus;
    protected boolean healthStatus;
    protected int hungerStatus;
    protected int age;
    protected int weight;
    protected int happiness;
    protected int foodResponse;
    protected int playResponse;
    protected String catchPhrase;
    protected String birthday;
    /**
     * Constructor for instance of pet data
     * @param name pet name
     * @param lifeStatus pet life status
     * @param healthStatus pet health status
     * @param hungerStatus pet hunger status
     * @param age pet age
     * @param weight pet weight
     * @param happiness pet happiness
     * @param birthday pet birthday
     */
    public PetData(String name, boolean lifeStatus, boolean healthStatus, int hungerStatus,
                   int age, int weight, int happiness, String birthday){
        this.name = name;
        this.lifeStatus = lifeStatus;
        this.healthStatus = healthStatus;
        this.hungerStatus = hungerStatus;
        this.age = age;
        this.weight = weight;
        this.happiness = happiness;
        this.birthday = birthday;
    }
    /**
     * Retrieves Pet name
     * @return String
     */
    public String getName() {return name;}
    /**
     * Sets pet name
     * @param name Pet name
     */
    public void setName(String name) {this.name = name;}
    /**
     * Retrieves life status of pet
     * @return True if the pet is still alive
     */
    public boolean getLifeStatus() {return lifeStatus;}
    /**
     * Sets life status of the pet
     * @param lifeStatus boolean
     */
    public void setLifeStatus(boolean lifeStatus) {this.lifeStatus = lifeStatus;}
    /**
     * Retrieves health status of the pet
     * @return True if the pet is healthy
     */
    public boolean getHealthStatus() {return healthStatus;}
    /**
     * Sets the health status of the pet
     * @param health boolean
     */
    public void setHealthStatus(boolean health) {this.healthStatus = health;}
    /**
     * Retrieves the hunger stat for the pet
     * @return int
     */
    public int getHungerStatus() {return hungerStatus;}
    /**
     * Set the hunger status of the pet
     * @param hunger int
     */
    public void setHungerStatus(int hunger) {this.hungerStatus = hunger;}
    /**
     * Retrieves the age of the pet
     * @return int
     */
    public int getAge() {return age;}
    /**
     * Sets the age of the pet
     * @param age int
     */
    public void setAge(int age) {this.age = age;}
    /**
     * Sets the weight of the pet
     * @param weight int
     */
    public void setWeight(int weight){this.weight = weight;}
    /**
     * Retrieves the happiness stat for the pet
     * @return int
     */
    public int getHappiness() {return happiness;}
    /**
     * Sets happiness stat for the pet
     * @param happiness int
     */
    public void setHappiness(int happiness) {this.happiness = happiness;}
    /**
     * Retrieves the food response for the pet
     * @return int
     */
    public int getFoodResponse() {return foodResponse;}
    /**
     * Sets the food response for the pet
     * @param foodResponse int
     */
    public void setFoodResponse(int foodResponse) {this.foodResponse = foodResponse;}
    /**
     * Retrieves the play response
     * @return int
     */
    public int getPlayResponse() {return playResponse;}
    /**
     * Sets play response for the pet
     * @param playResponse int
     */
    public void setPlayResponse(int playResponse) {this.playResponse = playResponse;}
    /**
     * retrieves catchphrase for the pet
     * @return String
     */
    public String getCatchPhrase() {return catchPhrase;}
    /**
     * Sets catchphrase for the pet
     * @param catchPhrase String
     */
    public void setCatchPhrase(String catchPhrase) {this.catchPhrase = catchPhrase;}

    /**
     * Prepares the data to present to a presenter class.
     * @return String
     */
    @Override
    public String show() {
        return null;
    }
}
