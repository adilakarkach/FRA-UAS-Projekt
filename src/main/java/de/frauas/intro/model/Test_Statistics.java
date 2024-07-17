package de.frauas.intro.model;

/**
 * This class creates an object of the type Test_Statistics
 * It stores all percentages of picked answers in the test 
 * 
 * @author Adil Akarkach
 *
 */
public class Test_Statistics {
	
	private int percSH;
	private int percNU;
	private int percKH;
	private int percSU;
	private int otherUsers;
	
	/**
	 * This method is the constructor for creating Test_Statistics
	 * 
	 * @param percSH Is an integer which is the rate of how many times SH was picked in percentage
	 * @param percNU Is an integer which is the rate of how many times NU was picked in percentage
	 * @param percKH Is an integer which is the rate of how many times KH was picked in percentage
	 * @param percSU Is an integer which is the rate of how many times SU was picked in percentage
	 * @param otherUsers Is an integer which is the rate of how many times the character was picked
	 * by all users in average as percent
	 */
	public Test_Statistics(int percSH, int percNU, int percKH, int percSU, int otherUsers) {
		super();
		this.percSH = percSH;
		this.percNU = percNU;
		this.percKH = percKH;
		this.percSU = percSU;
		this.otherUsers = otherUsers;
	}
	public int getPercSH() {
		return percSH;
	}
	public void setPercSH(int percSH) {
		this.percSH = percSH;
	}
	
	public int getPercNU() {
		return percNU;
	}
	public void setPercNU(int percNU) {
		this.percNU = percNU;
	}
	public int getPercKH() {
		return percKH;
	}
	public void setPercKH(int percKH) {
		this.percKH = percKH;
	}
	public int getPercSU() {
		return percSU;
	}
	public void setPercSU(int percSU) {
		this.percSU = percSU;
	}
	public int getOtherUsers() {
		return otherUsers;
	}
	public void setOtherUsers(int otherUsers) {
		this.otherUsers = otherUsers;
	}
	
	

	

}
