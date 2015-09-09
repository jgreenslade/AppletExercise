package edu.elon.contact;

import java.util.ArrayList;

import edu.elon.util.DBUtil;

public class ContactController {
	
	// Contants
	// Private variables
	private Gui gui;
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
		
		Contact me = new Contact(2, "Jacopo", "Rush", "Greenslade", "jgreenslade@elon.edu", "CS");
		contacts.add(me);
		
		gui.getContactInfo(contacts, 0);
		
		contacts = null;
		contacts = DBUtil.getContacts("root", "mysqluser", "jdbc:mysql://localhost:3306", "", "", "");
		
		for (Contact c: contacts) {
			System.out.println(c.toString());
		}
	
		
	}

}
