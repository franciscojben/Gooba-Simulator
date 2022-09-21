package library.presenters.presenterInterfaces;

/**
 * Interface for TradeManager
 *
 * @author group_0236
 */
public interface ITradeManager {

    /**
     * Response for when a trade is accepted
     * @param response String
     */
    void responseAcceptTrade(String response);

    /**
     * Response for when a trade is sent
     * @param response String
     */
    void responseSendTrade(String response);

    /**
     * Response for when a trade is canceled
     * @param response String
     */
    void responseCancelTrade(String response);

    /**
     * Response for when a user wants to see their trade requests
     * @param response String
     */
    void responseViewTradeRequests(String response);
}
