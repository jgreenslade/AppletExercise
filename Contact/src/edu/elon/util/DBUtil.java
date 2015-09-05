package edu.elon.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.elon.contact.Contact;

public class DBUtil {

	public static void addContact() {

	}

	public static void removeContact(int id, Statement stmt) {
		String query = "REMOVE FROM contact_db.contacts WHERE id = " + id;
	}

	public static void removeAll(Contact[] c, Statement stmt) {
		// Loop through contacts and delete them all
		for (Contact contact : c) {
			removeContact(contact.getIndex(), stmt);
		}
	}

	public static void update(Contact c, Statement stmt) {
		String query = "UPDATE contact_db.contacts "
				+ "SET first_name='" + c.getfName() + "',"
					+ "middle_name='" + c.getfName() + "',"
					+ "last_name='" + c.getfName() + "',"
					+ "email='" + c.getfName() + "',"
					+ "major='" + c.getfName() + "',";
	}

	public static ResultSet getContacts(Statement stmt) {
		String query = "SELECT * FROM contact_db.contacts";
		try {
			return stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Exception : " + e);
		}
		return null;
	}

}
