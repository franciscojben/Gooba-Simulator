package library.presenters.presenterInterfaces;

/**
 * Interface for PetManager
 *
 * @author group_0236
 */
public interface IPetManager {

    /**
     * Response when a pet was fed
     * @param response String
     */
    void responseFeed(String response);

    /**
     * Response when a pet is played with
     * @param response String
     */
    void responsePlayWith(String response);

    /**
     * Response when a pet is added
     * @param response String
     */
    void responseAddPet(String response);

    /**
     * Response when a pet is swapped
     * @param response String
     */
    void responseSwap(String response);

    /**
     * Response when a pet dies
     * @param response String
     */
    void responseDie(String response);

    /**
     * Response when a pet hatches
     * @param response String
     */
    void responseHatch(String response);

    /**
     * Response when a pet is talked to
     * @param response String
     */
    void responseTalk(String response);

    /**
     * Response when a pet is buried
     * @param response String
     */
    void responseBury(String response);

    /**
     * Response when a pet's age is prompted
     * @param response String
     */
    void responseSeeAge(String response);

    /**
     * Response when a pet's health is prompted
     * @param response String
     */
    void responseSeeHealth(String response);

    /**
     * Response when the graveyard is prompted
     * @param graveyard String
     */
    void responseGraveyard(String graveyard);
}
