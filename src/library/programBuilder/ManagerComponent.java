package library.programBuilder;

import library.modules.loginSystem.loginUseCases.DataManager;
import library.modules.loginSystem.loginEntities.IUserBase;
import library.modules.loginSystem.loginEntities.UserBase;
import library.modules.loginSystem.loginUseCases.AccountManager;
import library.modules.loginSystem.loginUseCases.LoginManager;
import library.modules.loginSystem.loginUseCases.UserManager;
import library.util.IObserverManager;
import library.util.ObserverManager;
import library.modules.followSystem.followUseCases.FollowManager;
import library.modules.leaderboardSystem.leaderboardUseCases.LeaderboardManager;
import library.modules.petSystem.petUseCases.PetManager;
import library.modules.petSystem.petUseCases.TimerManager;
import library.modules.tradeSystem.tradeUseCases.TradeManager;
import library.modules.notificationSystem.notificationUseCases.NotificationManager;

/**
 * This class is responsible for building instances of the managers so that they can be injected
 * in the loginControllers.
 *
 * @author group_0236
 */
public class ManagerComponent {
    private final IUserBase ub;
    private final IObserverManager obMan;
    private final UserManager um;
    private final FollowManager fm;
    private final AccountManager am;
    private final PetManager pm;
    private final DataManager dm;
    private final LeaderboardManager lbm;
    private final LoginManager lm;
    private final NotificationManager nm;
    private final TradeManager trm;

    /**
     * Create an instance of the manager component.
     * <p>
     *     Some managers require the presenter to be injected.  Please construct the PresenterComponent
     *     first before constructing this component.
     * </p>
     * @param pre PresenterComponent
     */
    public ManagerComponent(PresenterComponent pre) {
        ub = new UserBase();
        obMan = new ObserverManager(ub);
        TimerManager tm = new TimerManager();
        um = new UserManager(ub, obMan, pre.getPresenter());
        fm = new FollowManager(ub, obMan, pre.getPresenter());
        am = new AccountManager(ub, obMan, pre.getPresenter());
        pm = new PetManager(am, ub, tm, pre.getPresenter());
        dm = new DataManager(ub, pre.getPresenter());
        lbm = new LeaderboardManager(ub, pre.getPresenter());
        lm = new LoginManager(obMan, pre.getPresenter());
        nm = new NotificationManager(ub, obMan, pre.getPresenter());
        trm = new TradeManager(pre.getPresenter(), pm, obMan);
    }

    /**
     * Get the user base.
     * @return IUserBase
     */
    public IUserBase getUb() {
        return ub;
    }

    /**
     * Get the observer manager.
     * @return IObserverManager
     */
    public IObserverManager getObMan() {
        return obMan;
    }

    /**
     * Get the user manager.
     * @return UserManager
     */
    public UserManager getUm() {
        return um;
    }

    /**
     * Get the follow manager.
     * @return FollowManager
     */
    public FollowManager getFm() {
        return fm;
    }

    /**
     * Get the account manager.
     * @return AccountManager
     */
    public AccountManager getAm() {
        return am;
    }

    /**
     * Get the pet manager.
     * @return PetManager
     */
    public PetManager getPm() {
        return pm;
    }

    /**
     * Get the data manager.
     * @return DataManager
     */
    public DataManager getDm() {
        return dm;
    }

    /**
     * Get the leaderboard manager.
     * @return LeaderboardManager
     */
    public LeaderboardManager getLbm() {
        return lbm;
    }

    /**
     * Get the login manager.
     * @return LoginManager
     */
    public LoginManager getLm() {
        return lm;
    }

    /**
     * Get the notification manager.
     * @return NotificationManager
     */
    public NotificationManager getNm() {
        return nm;
    }

    /**
     * Get the trade manager.
     * @return TradeManager
     */
    public TradeManager getTrm(){return trm;}
}
