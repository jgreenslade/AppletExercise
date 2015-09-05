package edu.elon.contact;

import java.util.ArrayList;

import edu.elon.util.DBUtil;

public class ContactController {
	
	// Contants
	// Private variables
	private Gui gui;
	private DBConnection dbConnection;
	
	private ArrayList<Contact> contacts;
	
	/**
	 * Standard Empty constructor
	 */
	public ContactController() {
		// Standard Empty constructor
		gui = new Gui();
		contacts= new ArrayList<Contact>();
	}
	/**
	 *  Starts the application
	 */
	public void go() {
		
		Contact me = new Contact("Jacopo", "Rush", "Greenslade", "jgreenslade@elon.edu", "CS", 1);
		contacts.add(me);
		
		gui.getContactInfo(contacts, 0);
		
		dbConnection = new DBConnection();
		DBUtil.getContacts(dbConnection.connect());
		
		dbConnection.close();
		
	}

}
