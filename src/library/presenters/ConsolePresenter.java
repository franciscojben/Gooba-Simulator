package library.presenters;

import library.util.interfaces.DataType;

/**
 * ConsolePresenter is the main presenter for the program.
 *
 * @author group_0236
 */
public class ConsolePresenter implements IPresenter {
    @Override
    public void showData(DataType dataObject) {
        System.out.println(dataObject.show());
    }

    @Override
    public void responseUser(String response) {
        System.out.println("<User> " + response);
    }

    @Override
    public void responseLogin(String response) {
        System.out.println("<Login> " + response);
    }

    @Override
    public void show(String show) {
        System.out.println("Showing... " + "\n" + show);
    }
    @Override
    public void responseLike(String response) {
        System.out.println("<Like> " + response);
    }

    @Override
    public void responseFavourite(String response) {
        System.out.println("<Favourite> " + response);
    }

    @Override
    public void responseFollow(String response){
        System.out.println("<Follow> " + response);
    }
    @Override
    public void responseLogout(String response) {
        System.out.println("<Logout> " + response);
    }

    @Override
    public void responseFeed(String response) {
        System.out.println("<Food> " + response);
    }

    @Override
    public void responsePlayWith(String response) {
        System.out.println("<Play> " + response);
    }

    @Override
    public void responseAddPet(String response) {
        System.out.println("<Pet> " + response);
    }

    @Override
    public void responseSwap(String response) {
        System.out.println("<Swap> " + response);
    }

    @Override
    public void responseDie(String response) {
        System.out.println("Oh no! " + response);
    }

    @Override
    public void responseHatch(String response) {
        System.out.println("<Hatch> " + response);
    }

    @Override
    public void responseTalk(String response) { System.out.println("<Talk> " + response);}

    @Override
    public void responseBury(String response) { System.out.println("<Bury> " + response);}
    @Override
    public void present(String response) {
        System.out.println(response);
    }

    @Override
    public void responseLeaderboard(String response) {
        System.out.println("<Leaderboard> " + response);
    }

    @Override
    public void responseAcceptTrade(String response) {
        System.out.println(response);
    }

    @Override
    public void responseSendTrade(String response) {
        System.out.println(response);
    }

    @Override
    public void responseCancelTrade(String response) {
        System.out.println(response);
    }

    @Override
    public void responseViewTradeRequests(String response) {
        System.out.println(response);
    }

    @Override
    public void responseSeeAge(String response) {System.out.println(response);}

    @Override
    public void responseSeeHealth(String response) {System.out.println(response);}

    @Override
    public void responseGraveyard(String response) {
        System.out.println("<Graveyard> " + response);
    }
}
