package library.modules.petSystem.petEntities;

import library.modules.loginSystem.loginEntities.User;
/**
 * Responsible for generating an instance of Uwasa.
 *
 * @author Umared44
 */
public class Uwasa extends Pet {
    /**
     * Constructor for new Uwasa
     * @param owner pet owner
     * @param name pet name
     */
    public Uwasa(User owner, String name) {
        super(owner, name);
        data.setWeight(30);
        data.setCatchPhrase("Uwaa~~~~!");
        data.setFoodResponse(7);
        data.setPlayResponse(1);
    }

    /**
     * Happiness increases when you play
     * @return String response to this action
     */
    public String playWith() {
        String response = "This Uwasa is dead.";
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
        String response = "This Uwasa is dead.";
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
        String response = "This Uwasa is dead.";
        if (data.lifeStatus){
            response = "You talk with " + getName() + ". \n \"" + data.getCatchPhrase() + "\"" ;
        }
        return response;
    }

    /**
     * Heal the pet from sickness.
     * @return String response to healing.
     */
    public String heal() {
        String response = "This Uwasa is dead.";
        if (data.lifeStatus) {
            if (!data.healthStatus) {
                data.setHungerStatus(3);
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
     * Get a string response for the pet Type
     * @return Type of pet
     */
    public String getPetType() { return "Uwasa";}

    /**
     * Allow the pet to die
     * @return String response to this action
     */
    public String die() {
        data.setLifeStatus(false);
        return getName() + " the Uwasa is dead.";
    }
}
