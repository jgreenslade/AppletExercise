/**
 * (c) Copyright 2015, Jacopo Greenslade
 */

package edu.elon.contact;

import java.util.ArrayList;

import edu.elon.util.DBUtil;

/**
 * Controller Class that manages the interaction between GUI and Db activity
 * @author jgreenslade
 *
 */
public class ContactController {

	// Contants
	// Private variables
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
	 * Starts the application
	 */
	public void go() {
		gui.makeGUI();
	}

	public void refreshGui() {
		if (contacts.size() > 0) {
			gui.getContactInfo(contacts.get(activeContact));
		}
	}

	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}

	public int getActiveContact() {
		return activeContact;
	}

	public void setActiveContact(int activeContact) {
		if (activeContact >= 0 && activeContact < contacts.size()) {
			this.activeContact = activeContact;
		}
	}

}
