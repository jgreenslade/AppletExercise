/**
 * (c) Copyright 2015, Jacopo Greenslade
 */

package edu.elon.contact;

import java.util.ArrayList;

import edu.elon.util.DBUtil;

/**
 * Controller Class that manages the interaction between GUI and Db activity
 * 
 * @author jgreenslade
 *
 */
public class ContactController {

	private Gui gui;
	public ArrayList<Contact> contacts;
	private int activeContact;

	/**
	 * Standard Empty constructor
	 */
	public ContactController() {
		// Standard Empty constructor
		gui = new Gui(this);
		contacts = new ArrayList<Contact>();
	}

	/**
	 * Makes the gui start the application
	 */
	public void go() {
		gui.makeGUI();
	}

	/**
	 * Refreshes the GUI main screen
	 */
	public void refreshGui() {
		if (contacts.size() > 0) {
			gui.getContactInfo(contacts.get(activeContact));
		}
	}

	/**
	 * gets the contacts arraylist @return, ArrayList of type Contact
	 */
	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	/**
	 * sets the value of the contact ArrayList
	 * 
	 * @param contacts,
	 *            ArrayList of type Contact
	 */
	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}

	/**
	 * get value of current active contact @return, int active contact
	 */
	public int getActiveContact() {
		return activeContact;
	}

	/**
	 * set active contact. Must be between 0 and the # of contacts
	 * 
	 * @param activeContact,
	 *            int value
	 */
	public void setActiveContact(int activeContact) {
		if (activeContact >= 0 && activeContact < contacts.size()) {
			this.activeContact = activeContact;
		}
	}

}
