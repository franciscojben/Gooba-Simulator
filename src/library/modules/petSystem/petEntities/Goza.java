package library.modules.petSystem.petEntities;

import library.modules.loginSystem.loginEntities.User;
/**
 * Responsible for generating an instance of Goza.
 *
 * @author Umared44
 */
public class Goza extends Pet {
    /**
     * Constructor for new Goza
     * @param owner pet owner
     * @param name pet name
     */
    public Goza(User owner, String name) {
        super(owner, name);
        data.setWeight(5);
        data.setCatchPhrase("Go-za");
        data.setFoodResponse(20);
        data.setPlayResponse(1);
    }

    public Goza(String name, User owner, boolean lifeStatus, boolean healthStatus, int hungerStatus, int age, int weight, int happiness, String birthday){
        super(name, owner, lifeStatus, healthStatus, hungerStatus, age, weight, happiness, birthday);
        data.setWeight(150);
        data.setCatchPhrase("Mo! Mo!");
        data.setFoodResponse(15);
        data.setPlayResponse(1);
    }

    /**
     * Happiness increases when you play
     * @return String response to this action
     */
    public String playWith() {
        String response = "This Goza is dead.";
        if (data.lifeStatus) {
            data.setHappiness(Math.min(100, data.getHappiness() + data.getPlayResponse()));
            response = "You play with " + getName() + ". Its happiness is now " + this.data.getHappiness() + ".";
        }
        return response;
    }

    /**
     * Hunger decreases when you feed it
     * @return String response to this action
     */
    public String feed() {
        String response = "This Goza is dead.";
        if (data.lifeStatus) {
            data.setHungerStatus(Math.max(0, data.getHungerStatus() - data.getFoodResponse()));
            response = "You feed " + getName() +"." + " Its hunger is now " + this.data.getHungerStatus() + ".";
        }
        return response;
    }

    /**
     * Talk to the Pet
     * @return this pet's catchphrase
     */
    public String talk(){
        String response = "This Goza is dead.";
        if (data.lifeStatus){
            response = "You talk with " + getName() + ". \n \"" + data.getCatchPhrase() + "\"" ;
        }
        return response;
    }

    /**
     * Get a string response for the pet Type
     * @return Type of pet
     */
    public String getPetType() { return "Goza";}

    /**
     * Heal the pet from sickness.
     * @return String response to healing.
     */
    public String heal() {
        String response = "This Goza is dead.";
        if (data.lifeStatus) {
            if (!data.healthStatus) {
                data.setHungerStatus(10);
                data.setHealthStatus(true);
                data.setHappiness(5);
                response = getName() + " takes some meds. It feels a little better now.";
            } else {
                response = getName() + " does not need meds right now.";
            }
        }
        return response;
    }

    /**
     * Allow the pet to die
     * @return String response to this action
     */
    public String die() {
        data.setLifeStatus(false);
        return getName() + " the Goza is dead.";
    }
}
