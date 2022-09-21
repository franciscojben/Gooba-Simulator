package library.modules.loginSystem.loginEntities;

import library.modules.petSystem.petEntities.Graveyard;
import library.modules.tradeSystem.tradeEntities.TradeRequest;
import library.util.interfaces.DataType;
import library.modules.notificationSystem.notificationEntities.Notification;
import library.modules.notificationSystem.notificationEntities.NotificationCollection;
import library.modules.notificationSystem.NoNotificationException;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * AccountData stores all the user's likes, favourites, and other user information.
 *
 * @author group_0236
 */
public class AccountData implements DataType, Serializable, PropertyChangeListener {
	/* This class stores the fundamental account information
	 * of the user.  The account ID cannot be changed as it is used
	 * to determine account ownership.
	 */
	// accountID is based on the number of accounts
	private static int accountNum;
	private final int accountID;
	private final String accountName;
	private boolean accountBanStatus;
	private User favourite;
	private int numLikes;
	private final ArrayList<String> likedUsers = new ArrayList<>();
	private final NotificationCollection notifications = new NotificationCollection();
	private final Graveyard graveyard = new Graveyard();
	private int deathCount = 0;
	private final ArrayList<TradeRequest> tradeRequests = new ArrayList<>();
	/**
	 * Constructs an instance of AccountData.
	 * <p>
	 *     This is called when the user signs up.  It makes an account with
	 *     a generic name based on the ID of the account.
	 * </p>
	 */
	public AccountData() {
		this.accountID = accountNum;
		this.accountName = Integer.toString(this.accountID);
		this.accountBanStatus = false;
		this.numLikes = 0;
		accountNum++;
	}

	// Constructor for reading from db

	/**
	 * Constructs an instance of AccountData.
	 * <p>
	 *     This is called for reading from the database.
	 * </p>
	 * @param accountId int
	 * @param name String
	 * @param banStatus boolean
	 * @param likes int
	 * @param data String
	 */

	public AccountData(int accountId, String name, boolean banStatus, int likes, String data) {
		this.accountID = accountId;
		this.accountName = name;
		this.accountBanStatus = banStatus;
		this.numLikes = likes;

		String[] parsedData = data.split(";");
		for (String username : parsedData){
			likedUsers.add(username.strip());
		}
		accountNum++;
	}

	// DO NOT USE (ONLY IMPLEMENTED FOR UNIT TESTING PURPOSES)

	/**
	 * DO NOT USE
	 * <p>
	 *     Sets the number of accounts in the system.  Changing this number
	 *     will break how AccountData generates unique IDs.
	 * </p>
	 * @param num int
	 */
	public void setAccountNum(int num) {
		accountNum = num;
	}

	/**
	 * Get the account ID.
	 * @return int
	 */
	public int getAccountID() {
		return this.accountID;
	}

	/**
	 * Get the account name.
	 * @return String
	 */
	public String getName() {
		return this.accountName;
	}


	/**
	 * Get the account's ban status.  (Potential removal in the future)
	 * @return boolean
	 */
	public boolean getBanStatus() {
		return this.accountBanStatus;
	}

	/**
	 * Set the account's ban status.  (Potential removal in the future)
	 * @param status boolean
	 */
	public void setBanStatus(boolean status) {
		this.accountBanStatus = status;
	}

	/**
	 * Get the number of likes the user has.
	 * @return int
	 */
	public int getNumLikes() {
		return this.numLikes;
	}

	/**
	 * Like a user.
	 * @param user User
	 */
	public void like(User user) {
		this.numLikes++;
		likedUsers.add(user.username);
	}

	/**
	 * Unlike a user.
	 * @param user User
	 */
	public void unlike(User user) {
		this.numLikes--;
		likedUsers.remove(user.username);
	}

	/**
	 * Check if a liked user is contained within a user's liked list.
	 * @param user User
	 * @return boolean
	 */
	public boolean contains(User user){
		return likedUsers.contains(user.username);
	}

	/**
	 * Set the user's favourite user on the system.
	 * @param favourite User
	 */
	public void setFavourite(User favourite) {
		this.favourite = favourite;
	}

	/**
	 * Get the user's favourite user.
	 * @return User
	 */
	public String getFavourite() {
		return this.favourite.getUsername();
	}

	/**
	 * Updates the graveyard with a dead pet's name
	 * @param name updates the name variable
	 */
	public void updateGraveyard(String name){
		graveyard.updateGraveyard(name);
	}

	public List<String> getGraveyard(){
		return graveyard.getGraveyard();
	}

	public boolean isDeaths(){
		return graveyard.isDeaths();
	}


	/**
	 * Gets the user's death count
	 * @return int
	 */
	public int getDeathCount(){ return deathCount; }

	/**
	 * Adds one to the user's death count
	 */
	public void addDeathCount(){
		deathCount++;
	}

	/**
	 * Presents accountData for use in the DataManager class.
	 * @return String
	 */
	public String show() {
		return "Name: " + accountName + "\n" + "ID: " + accountID + "\n";
	}
	@Override
	public String toString() {
		String likedUsersString = "null";
		if (!likedUsers.isEmpty()){
			likedUsersString = likedUsers.toString();
		}
		return getAccountID() + ";" + getName() + ";"
				+ getBanStatus() + ";" + getNumLikes() + ";" + likedUsersString;
	}

	/**
	 * Add a notification to the user's account.
	 * @param notification Notification
	 */
	public void addNotification(Notification notification) {
		notifications.addNotification(notification);
	}

	/**
	 * Get the user's latest notification.
	 * @return Notification
	 * @throws NoNotificationException No notification exception
	 */
	public Notification getNotification() throws NoNotificationException {
		return notifications.getNotification();
	}
	public List<TradeRequest> getTradeRequests(){
		return this.tradeRequests;
	}

	/**
	 * Get the number of notifications the user has.
	 * @return int
	 */
	public int getNotificationCount() {
		return notifications.size();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("cancelTrade")){
			this.tradeRequests.remove(evt.getNewValue());
		}else if (evt.getPropertyName().equals("addTrade")){
			this.tradeRequests.add((TradeRequest) evt.getNewValue());
		}
	}
}
