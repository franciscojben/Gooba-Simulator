package library.modules.tradeSystem.tradeUseCases;

import library.modules.loginSystem.loginEntities.User;
import library.modules.tradeSystem.tradeEntities.TradeRequest;
import library.util.IObserverManager;
import library.modules.petSystem.petUseCases.PetManager;
import library.presenters.presenterInterfaces.ITradeManager;

/**
 * Use case for the trade module
 */
public class TradeManager {
    private final ITradeManager presenter;
    private final PetManager pm;
    private final IObserverManager obMan;

    /**
     * Create an instance of Trade Manager
     * @param presenter presenter
     * @param pm pet manager
     * @param obMan observer manager
     */
    public TradeManager(ITradeManager presenter, PetManager pm, IObserverManager obMan){
        this.presenter = presenter;
        this.pm = pm;
        this.obMan = obMan;
    }

    /**
     * Send a trade request to other user from current user
     * @param currentUser current user
     * @param otherUser other user
     * @return true if trade was successful
     */
    @SuppressWarnings("SameReturnValue")
    public boolean sendTrade(User currentUser, User otherUser){
        String response = "Trade request to " + otherUser.getUsername() + " has been sent.";
        new TradeRequest(currentUser, otherUser);
        obMan.updateUsers();
        presenter.responseSendTrade(response);
        return true;
    }

    /**
     * Accept the trade at the given index and swap pets
     * @param index Index of trade in list of trade requests
     * @param currentUser current user
     */
    public void acceptTrade(int index, User currentUser){
        TradeRequest trade = currentUser.getTradeRequests().get(index);
        String response = "Trade request from " + trade.getSender().getUsername() + " has been accepted. Say hello to" +
                " your new pet " + trade.getSender().getPet();
        User otherUser = trade.getSender();
        pm.swapPets(otherUser.getUsername(), currentUser.getUsername());
        trade.cancel();
        pm.updatePets();
        obMan.updateUsers();
        presenter.responseAcceptTrade(response);
    }

    /**
     * Cancel the trade at the given index
     * @param index Index of trade in list of trade requests
     * @param currentUser current user
     */
    public void cancelTrade(int index, User currentUser){
        TradeRequest trade = currentUser.getTradeRequests().get(index);
        String response = "Trade request from " + trade.getSender().getUsername() + " has been declined.";
        trade.cancel();
        obMan.updateUsers();
        presenter.responseCancelTrade(response);
    }

    /**
     * Present all trade requests for this user
     * @param currentUser current user
     * @return true if there is at least one trade request
     */
    public boolean viewTradeRequests(User currentUser){
        String response = "No trade requests available.";

        boolean trades = false;
        if (currentUser.getTradeRequests().size() > 0){
            int index = 0;
            StringBuilder sb = new StringBuilder();
            for (TradeRequest item : currentUser.getTradeRequests()){
                sb.append(index).append("-").append(item.toString()).append("\n");
                index += 1;
            }
            trades = true;
            response = String.valueOf(sb);
        }
        presenter.responseViewTradeRequests(response);
        return trades;
    }

    /**
     * @param index Index of trade in list of trade requests
     * @param currentUser current user
     * @return true if this trade is an incoming trade
     */
    public boolean isIncoming(int index, User currentUser){
        return currentUser.getTradeRequests().get(index).getReceiver().equals(currentUser);
    }

}
