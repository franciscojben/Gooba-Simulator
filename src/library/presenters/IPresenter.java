package library.presenters;

import library.presenters.presenterInterfaces.LoginPresenter;
import library.presenters.presenterInterfaces.PetPresenter;

/**
 * IPresenter defines what the presenter needs to show all possible data in the program.
 *
 * @author group_0236
 */
public interface IPresenter extends LoginPresenter, PetPresenter {
    /**
     * Present a generic response to the console.
     * @param response String
     */
    void present(String response);

    /**
     * A response to the leaderboard.
     * @param response String
     */
    void responseLeaderboard(String response);

    /**
     * A response to checking the age.
     * @param response String
     */
    void responseSeeAge(String response);
}
