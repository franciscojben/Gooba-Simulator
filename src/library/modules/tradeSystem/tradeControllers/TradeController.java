package library.modules.tradeSystem.tradeControllers;

import library.modules.loginSystem.loginUseCases.UserManager;
import library.modules.loginSystem.loginEntities.User;
import library.modules.tradeSystem.tradeUseCases.TradeManager;

/**
 * Controller for trade module
 */
public class TradeController {
    private final TradeManager tm;
    private final UserManager um;

    /**
     * Create an instance of trade tradeControllers
     * @param tm Trade Manager
     * @param um User Manager
     */
    public TradeController(TradeManager tm, UserManager um){
        this.tm = tm;
        this.um = um;
    }

    /**
     * Present all trade requests
     * @param currentUser current user
     * @return true if there are any trade requests
     */
    public boolean viewTradeRequests(User currentUser){
        return tm.viewTradeRequests(currentUser);
    }

    /**
     * Send a trade from current user to other user
     * @param currentUser current user
     * @param otherUser username of the other user you want to send a trade to
     * @return true if successfully sent a trade
     */
    public boolean sendTrade(User currentUser, String otherUser){
        if (um.getUser(otherUser) != null){
            return tm.sendTrade(currentUser, um.getUser(otherUser));
        }
        return false;
    }

    /**
     * Cancel this trade at index
     * @param index index of the trade
     * @param currentUser current user
     */
    public void cancelTrade(int index, User currentUser){
        tm.cancelTrade(index, currentUser);
    }

    /**
     * Accept this trade at index
     * @param index index of the trade
     * @param currentUser current user
     */
    public void acceptTrade(int index, User currentUser){
        tm.acceptTrade(index, currentUser);
    }

    /**
     * @param index index of the trade
     * @param currentUser current user
     * @return True if this is an incoming trade
     */
    public boolean isIncoming(int index, User currentUser){
        return tm.isIncoming(index, currentUser);
    }

    /**
     * @param index index of the trade
     * @param currentUser current user
     * @return True if a trade exists at this index
     */
    public boolean isValid(int index, User currentUser){
        return currentUser.getTradeRequests().size() > index;
    }
}
