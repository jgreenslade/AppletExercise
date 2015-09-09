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
		contacts = new ArrayList<Contact>();
	}

	/**
	 * Starts the application
	 */
	public void go() {

		Contact me = new Contact(1, "Jacopo", "Rush", "Greenslade", "jgreenslade@elon.edu", "CS");
		contacts.add(me);
		
		gui.makeGUI();
		gui.getContactInfo(contacts, 0);
		
		DBUtil.setDBParameters("root", "mysqluser", "jdbc:mysql://localhost:3306", "contact_db", "contacts");
		contacts = DBUtil.getContacts();
		System.out.println("Test getContacts()");
		for (Contact c : contacts) {
			System.out.println(c.toString());
		}
		
//		System.out.println("Test removeContact() : should be missing Potato Head");
//		DBUtil.removeContact(3);
//		contacts = DBUtil.getContacts();
//		for (Contact c : contacts) {
//			System.out.println(c.toString());
//		}
//		
//		System.out.println("Test removeAll()");
//		DBUtil.removeAll();
//		contacts = DBUtil.getContacts();
//		for (Contact c : contacts) {
//			System.out.println(c.toString());
//		}
//		System.out.println("Test updateContact() : change my name");
//		me.setfName("Opocaj");
//		me.seteMail("ogreenslade@nole.ude");
//		DBUtil.updateContact(me);
//		contacts = DBUtil.getContacts();
//		for (Contact c : contacts) {
//			System.out.println(c.toString());
//		}
//		System.out.println("Test insertContact()");
//		me = new Contact(1, "Bubu", "CACA", "NONO", "infant@baby.child", "Cuteness");
//		DBUtil.insertContact(me);
//		contacts = DBUtil.getContacts();
//		for (Contact c : contacts) {
//			System.out.println(c.toString());
//		}
	}

	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}

}
