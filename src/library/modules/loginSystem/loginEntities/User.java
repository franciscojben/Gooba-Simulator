package library.modules.loginSystem.loginEntities;

import library.modules.tradeSystem.tradeEntities.TradeRequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User is an abstract tradeEntities class that stores user information about the client
 *
 * @author group_0236
 */
public abstract class User implements Serializable{
    protected String username;
    protected String password;
    protected boolean banStatus = false;
    protected boolean adminStatus = false;
    protected final AccountData accountData;
    protected final HistoryData historyData;
    protected final ArrayList<String> followers = new ArrayList<>();
    protected final ArrayList<String> following = new ArrayList<>();
    protected String petName;

    /**
     * Constructs the user object.
     * <p>
     * This method should not be used to create users.  This method is to help
     * RegUser and Admin be instantiated in an OOP manner.
     * <p>
     * @param username String
     * @param password String
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.accountData = new AccountData();
        this.historyData = new HistoryData();
    }


    /**
     * Constructs the user object for reading from the database.
     * <p>
     * This method should not be used to create users.  This method is to help
     * RegUser and Admin be instantiated in an OOP manner.
     * </p>
     *
     * @param username    String
     * @param password    String
     * @param banStatus   boolean
     * @param accountData accountData
     * @param historyData historyData
     */
    public User(String username, String password, boolean banStatus,
                String accountData, String historyData, String petName) {
        this.username = username;
        this.password = password;
        this.banStatus = banStatus;
        String[] parsedAccountData = accountData.split(";");
        this.accountData = new AccountData(Integer.parseInt(parsedAccountData[0]), parsedAccountData[1],
                Boolean.parseBoolean(parsedAccountData[2]),Integer.parseInt(parsedAccountData[3]),parsedAccountData[4]);
        this.historyData = new HistoryData(historyData);
        this.petName = petName;
    }

    /**
     * Get the user's account data.
     * @return accountData
     */
    public AccountData getAccountData() {
        return accountData;
    }

    /**
     * Get the user's history data.
     * @return historyData
     */
    public HistoryData getHistoryData(){
        return historyData;
    }

    /**
     * Add history to the user.
     * @param loginHistory String
     */
    public void addHistoryData(String loginHistory) {this.historyData.addHistory(loginHistory);}

    /**
     * Get the user's username.
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Return this user's username. This exists for the Namable interface to work.
     * @return String
     */
    public String getName() {
        return this.getUsername();
    }
    /**
     * Set the user's username.
     * @param username String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the user's password.
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the user's password.
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public void setPet(String petName){this.petName = petName;}
    public void removePet(){this.petName = null;}
    public String getPet(){return this.petName;}

    /**
     * Set the user's ban status.
     * @param banStatus boolean
     */
    public void setBanStatus(boolean banStatus) {
        this.banStatus = banStatus;
        this.accountData.setBanStatus(banStatus);
    }

    /**
     * Get the user's ban status.
     * @return boolean
     */
    public boolean getBanStatus(){return this.banStatus;}

    /**
     * Get the user's admin status.
     * @return boolean
     */
    public boolean getAdminStatus() {
        return this.adminStatus;
    }

    /**
     * Follow another user.
     * @param user User
     */
    public void follow(User user) {
        this.following.add(user.getUsername());
        user.addFollowing(this);
    }

    private void addFollowing(User e) {
        this.followers.add(e.getUsername());
    }

    /**
     * Unfollow another user.
     * @param user User
     */
    public void unfollow(User user) {
        if (!this.following(user)) {
            // Do nothing... however IntelliJ complains that the 'if' statement has an empty body
            // so, keep that in consideration...
        } else {
            this.following.remove(user.getUsername());
            user.removeFollowing(this);
        }
    }

    private void removeFollowing(User e) {
        this.followers.remove(e.getUsername());
    }

    /**
     * Return if user is following another user.
     * @param user User
     * @return boolean
     */
    public boolean following(User user){
        return following.contains(user.getUsername());
    }

    /**
     * Get the user's followers list.
     * @return List[String]
     */
    public List<String> getFollowers() {
        return this.followers;
    }

    /**
     * Get the user's following list.
     * @return List[String]
     */
    public List<String> getFollowing() {
        return this.following;
    }

    /**
     * Updates this user's graveyard
     * @param petName String
     */
    public void updateGraveyard(String petName){
        accountData.updateGraveyard(petName);
    }

    /**
     * Get the user's graveyard
     * @return List[String]
     */
    public List<String> getGraveyard() {
        return accountData.getGraveyard();
    }

    public List<TradeRequest> getTradeRequests(){
        return this.accountData.getTradeRequests();
    }

    /**
     * Returns whether the user had buried pets
     * @return boolean
     */
    public boolean isDeaths(){
        return accountData.isDeaths();
    }

    /**
     * Gets the user's death count
     * @return int
     */
    public int getDeathCount(){ return accountData.getDeathCount(); }

    /**
     * Adds one to the user's death count
     */
    public void addDeathCount(){ accountData.addDeathCount(); }

    /**
     * Prepare data for database.
     * @return String
     */
    @Override
    public String toString(){
        // Converting adminStatus from boolean to a String of Admin or Reg is unnecessarily messy
        String admin = "Regular";
        if (this.adminStatus){
            admin = "Admin";
        }

        String accountData = this.accountData.toString();
        String historyData = this.historyData.getHistory().toString();

        historyData = historyData.replace("[", "");
        historyData = historyData.replace("]", "");
        historyData = historyData.replace(",", ";");
        historyData = historyData.strip();

        accountData = accountData.replace("[", "");
        accountData = accountData.replace("]", "");
        accountData = accountData.replace(",", ";");
        accountData = accountData.strip();


        // Current format of history and account data makes passing them as a string in the db difficult
        return this.username + "," + this.password + "," + admin + "," +
                this.banStatus + ","+ accountData + ","+ historyData + "," + this.petName;
    }

}
