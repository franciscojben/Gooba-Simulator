package library.modules.petSystem.petControllers;

import library.modules.loginSystem.loginEntities.User;
import library.modules.petSystem.petUseCases.PetManager;

public class PetController {
    private final PetManager pm;
    /**
     * Constructor for Pet Controller
     * @param pm Observer to be added
     */
    public PetController(PetManager pm) {
        this.pm = pm;
    }
    /**
     * Notifies PetManager to create a new egg
     * @param name Pet name
     * @param owner Pet owner
     */
    public boolean createEgg(String name, User owner) {
        return pm.createEgg(name, owner);}
    /**
     * Notifies PetManager to carry out play action
     * @param name Pet name
     */
    public void playWith (String name) {
        pm.playWith(name);
    }
    /**
     * Notifies PetManager to carry out feed action
     * @param name Pet name
     */
    public void feed(String name) {pm.feed(name);}
    /**
     * Notifies PetManager to carry out talk action
     * @param name Pet name
     */
    public void talk(String name){pm.talk(name);}
    /**
     * Notifies PetManager to carry out kill action
     * @param name Pet name
     */
    public void die(String name) {pm.die(name);}
    /**
     * Notifies PetManager to bury the pet
     * @param name Pet name
     * @param owner Pet owner
     */
    public void bury(String name, User owner) {pm.bury(name, owner);}
    /**
     * Notifies PetManager to check if the pet is still alive
     * @param name Pet name
     * @return True if the pet is alive
     */
    public boolean isAlive(String name) {
        return pm.isAlive(name);
    }
    /**
     * Notifies PetManager to carry out feed the pet medicine
     * @param name Pet name
     */
    public void feedMeds(String name) {pm.feedMeds(name);}
    /**
     * Notifies PetManager to return the age of the pet
     * @param name Pet name
     */
    public void seeAge(String name) {pm.seeAge(name);}
    /**
     * Notifies PetManager to return the health status of the pet
     * @param name Pet name
     */
    public void seeHealth(String name) {pm.seeHealthStatus(name);}
    /**
     * Notifies PetManager to carry out play action
     * @param graveyard Pet graveyard
     */
    public void showGraveyard(String graveyard){pm.showGraveyard(graveyard);}
}
