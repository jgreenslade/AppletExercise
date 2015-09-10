/**
 * (c) Copyright 2015, Jacopo Greenslade
 */
package edu.elon.contact;

/**
 * This class represents a contact and contains all the contact information.
 * 
 * @author jgreenslade
 *
 */
public class Contact {

	private int index;
	private String fName;
	private String mName;
	private String lName;
	private String eMail;
	private String major;

	/**
	 * Standard empty constructor
	 */
	public Contact() {

	}

	/**
	 * Creates an instance of Contact with all the information
	 * 
	 * @param index,
	 *            int value of DB index
	 * @param fName,
	 *            String contact first name
	 * @param mName,
	 *            String contact middle name
	 * @param lName,
	 *            String contact last name
	 * @param eMail,
	 *            String contact email
	 * @param major,
	 *            String contact major
	 */
	public Contact(int index, String fName, String mName, String lName, String eMail, String major) {
		super();
		this.index = index;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.eMail = eMail;
		this.major = major;
	}

	/**
	 * Gets the contact's first name
	 * 
	 * @return String fFame
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * Sets new value for contact first name
	 * 
	 * @param fName,
	 *            String fName
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * Gets the contact's middle name
	 * 
	 * @return String mName
	 */
	public String getmName() {
		return mName;
	}

	/**
	 * Sets new value for contact middle name
	 * 
	 * @param mName,
	 *            String mName
	 */
	public void setmName(String mName) {
		this.mName = mName;
	}

	/**
	 * Gets the contact's last name
	 * 
	 * @return String lName
	 */
	public String getlName() {
		return lName;
	}

	/**
	 * Sets new value for contact last name
	 * 
	 * @param lName,
	 *            String lName
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}

	/**
	 * Gets the contact's eMail
	 * 
	 * @return String eMail
	 */
	public String geteMail() {
		return eMail;
	}

	/**
	 * sets contact's email address
	 * 
	 * @param eMail,
	 *            String email
	 */
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	/**
	 * Gets the contact's major
	 * 
	 * @return String major
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * Sets the contact's major
	 * 
	 * @param major
	 */
	public void setMajor(String major) {
		this.major = major;
	}

	/**
	 * Gets the DB index for the contact
	 * 
	 * @return String major
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets value for the DB index of the contact
	 * 
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Override toString() method for testing purposes
	 */
	@Override
	public String toString() {
		return index + ": " + fName + " " + mName + " " + lName + ", eMail= " + eMail + ", major= " + major + "]";
	}

}
