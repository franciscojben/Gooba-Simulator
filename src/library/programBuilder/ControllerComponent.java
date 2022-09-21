package library.programBuilder;

import library.databaseGateways.ISystem;
import library.databaseGateways.SystemAccessSER;
import library.modules.loginSystem.loginControllers.AccountController;
import library.modules.followSystem.followControllers.FollowController;
import library.modules.leaderboardSystem.leaderboardControllers.LeaderboardController;
import library.modules.petSystem.petControllers.PetController;
import library.modules.tradeSystem.tradeControllers.TradeController;

/**
 * This class is responsible for creating the loginControllers of the system.
 *
 * @author group_0236
 */
public class ControllerComponent {
    private final ISystem userSA;
    private final ISystem petSA;
    private final FollowController fc;
    private final PetController pc;
    private final LeaderboardController lbc;
    private final AccountController ac;
    private final TradeController trc;

    /**
     * Create an instance of the tradeControllers component, which is responsible for
     * building the main tradeControllers.
     * <p>
     *     Some loginControllers need to know where the DB is located and the managers it needs to
     *     call from.  This is why SystemComponent and ManagerComponent are parameters for this
     *     constructor.  Please build those components first before building an instance of this.
     * </p>
     * @param sys SystemComponent
     * @param man ManagerComponent
     */
    public ControllerComponent(SystemComponent sys, ManagerComponent man) {
        userSA = new SystemAccessSER(sys.getUserPath(), man.getUm());
        petSA = new SystemAccessSER(sys.getPetPath(), man.getPm());
        fc = new FollowController(man.getFm(), man.getNm());
        pc  = new PetController(man.getPm());
        lbc = new LeaderboardController(man.getLbm());
        ac = new AccountController(man.getAm(), man.getDm(), man.getNm());
        trc = new TradeController(man.getTrm(), man.getUm());
    }

    /**
     * Get the user system access.
     * @return ISystem
     */
    public ISystem getUserSA() {
        return userSA;
    }

    /**
     * Get the pet system access.
     * @return ISystem
     */
    public ISystem getPetSA() {
        return petSA;
    }

    /**
     * Get the follower tradeControllers.
     * @return FollowController
     */
    public FollowController getFc() {
        return fc;
    }

    /**
     * Get the pet tradeControllers.
     * @return PetController
     */
    public PetController getPc() {
        return pc;
    }
    /**
     * Get the leaderboard tradeControllers.
     * @return LeaderboardController
     */
    public LeaderboardController getLbc() {
        return lbc;
    }
    /**
     * Get the account tradeControllers.
     * @return AccountController
     */
    public AccountController getAc() {
        return ac;
    }
    /**
     * Get the trade tradeControllers.
     * @return TradeController
     */
    public TradeController getTrc(){return trc;}
}
