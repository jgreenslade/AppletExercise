/**
 * (c) Copyright 2015, Jacopo Greenslade
 */
package edu.elon.contact;

public class Contact {
	
	private int index;
	private String fName;
	private String mName;
	private String lName;
	private String eMail;
	private String major;
	
	
	public Contact() {
		
	}

	public Contact( int index, String fName, String mName, String lName, String eMail, String major) {
		super();
		this.index = index;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.eMail = eMail;
		this.major = major;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	@Override
	public String toString() {
		return index + ": " + fName + " " + mName + " " + lName + ", eMail= "
				+ eMail + ", major= " + major + "]";
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	

}
