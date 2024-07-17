package de.frauas.intro.form;

/**
 * This class creates an object of the type UserForm
 * Its attributes are used for checking the input in the forum/comment box
 * when submitting a comment before storing it as an user object
 * 
 * @author Adil Akarkach
 *
 */
public class UserForm {
	private String userID;
	private String userName;
	private String userMessage;

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
