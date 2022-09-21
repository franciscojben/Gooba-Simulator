package library.modules.petSystem;

import library.modules.loginSystem.loginEntities.User;
import library.modules.petSystem.petEntities.*;
/**
 * Factory class responsible for creating new pets.
 *
 * @author Umared44
 */
public class PetFactory {
    /**
     * Factory method that creates new pets
     * @param petType int referring to the type of pet to be generated
     * @param owner Pet owner
     * @param name Name of new pet
     */
    public Pet makePet(int petType, User owner, String name) {
        if (petType == 1) {
            return new Gooba(owner, name);
        } else if (petType == 2) {
            return new Kikitchi(owner, name);
        } else if (petType == 3) {
            return new Momiji(owner, name);
        } else if (petType == 4) {
            return new Goza(owner, name);
        } else if (petType == 5) {
            return new Uwasa(owner, name);
        } else
            return null;
    }
}
