package de.frauas.intro.model;

/**
 * This class creates an object of the type Characters_Test
 * The object is the ID of the character with the highest pick rate from the user
 * They are used for calculating the rate of how much a character was picked
 * 
 * @author Adil Akarkach
 *
 */
public class Characters_Test {
	private String charID;

	public Characters_Test(String charID) {
		super();
		this.charID = charID;
	}

	public String getCharID() {
		return charID;
	}

	public void setCharID(String charID) {
		this.charID = charID;
	}

}
