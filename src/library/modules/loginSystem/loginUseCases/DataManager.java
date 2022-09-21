package library.modules.loginSystem.loginUseCases;

import library.modules.loginSystem.loginEntities.IUserBase;
import library.util.interfaces.DataType;
import library.presenters.presenterInterfaces.IDataManager;

/**
 * DataManager manages the data in the system (it currently manages user specific data)
 *
 * @author group_0236
 */
public class DataManager {
    final IUserBase userBase;
    final IDataManager presenter;

    /**
     * Create an instance of the DataManager.
     * @param userBase IUserBase
     * @param presenter IDataManager
     */
    public DataManager(IUserBase userBase, IDataManager presenter){
        this.userBase = userBase;
        this.presenter = presenter;
    }

    /**
     * Return the user's account data.
     * @param user String
     */
    public void getAccountData(String user){
        DataType accountData = userBase.getUser(user).getAccountData();
        presenter.showData(accountData);
    }

    /**
     * Return the user's history data.
     * @param user String
     */
    public void getHistoryData(String user){
        DataType historyData = userBase.getUser(user).getHistoryData();
        presenter.showData(historyData);
    }
}
