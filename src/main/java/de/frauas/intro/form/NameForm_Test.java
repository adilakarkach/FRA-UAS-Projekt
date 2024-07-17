package de.frauas.intro.form;

/**
 * This class creates an object of type NameForm_Test
 * It is used for checking whether input/chosen answers of user is valid
 * before storing it as a result
 * 
 * @author Adil Akarkach
 *
 */
public class NameForm_Test {
	private String userName_Test;
	private String userResult_Test;

	/**
	 * This method for getting the user name
	 * 
	 * @return A String for the variable userName_Test
	 */
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
