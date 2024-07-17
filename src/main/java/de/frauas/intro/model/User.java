package de.frauas.intro.model;

/**
 * This class creates an object of the type User
 * It stores the users post/comment in the forum/comment box
 * 
 * @author Adil Akarkach
 *
 */
public class User {
	private String userID;
	private String userName;
	private String userMessage;

	/**
	 * This is the constructor for creating User
	 * 
	 * @param userID Is a String which is the users ID
	 * @param userName Is a string which is the users name
	 * @param userMessage Is a string which is the users message/comment
	 */
	public User(String userID, String userName, String userMessage) {
		this.userID = userID;
		this.userName = userName;
		this.userMessage = userMessage;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
}
