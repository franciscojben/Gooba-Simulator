package library.modules.petSystem.petEntities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Responsible for creating a graveyard entity
 *
 * @author sanotoast
 */
public class Graveyard implements Serializable {
    private final List<String> deathList = new ArrayList<>();

    /**
     * Constructor for new Graveyard
     */
    public Graveyard(){
    }

    /**
     * Update this Graveyard with new dead pet
     * @param name Pet Name
     */
    public void updateGraveyard(String name){
        deathList.add(name);
    }

    /**
     * @return The list of pet's names who have died
     */
    public List<String> getGraveyard(){
        return deathList;
    }

    /**
     * @return Whether there have been deaths
     */
    public boolean isDeaths(){
        return (!deathList.isEmpty());
    }

}
