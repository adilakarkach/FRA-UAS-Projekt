package de.frauas.intro.model;

/**
 * This class creates an object of the type Name_Test
 * In it is the name of the user and their picked answers from the test stored
 * 
 * @author Adil Akarkach
 *
 */
public class Name_Test {
	private String userName_Test;
	private String userResult_Test;

	/**
	 * This is the constructor for creating Name_test
	 * 
	 * @param userName_Test Is a string which is the users name
	 * @param userResult_Test Is a string which is the users picked answers
	 */
	public Name_Test(String userName_Test, String userResult_Test) {
		super();
		this.userName_Test = userName_Test;
		this.userResult_Test = userResult_Test;
	}

	public String getUserName_Test() {
		return userName_Test;
	}

	public void setUserName_Test(String userName_Test) {
		this.userName_Test = userName_Test;
	}

	public String getUserResult_Test() {
		return userResult_Test;
	}

	public void setUserResult_Test(String userResult_Test) {
		this.userResult_Test = userResult_Test;
	}

}
