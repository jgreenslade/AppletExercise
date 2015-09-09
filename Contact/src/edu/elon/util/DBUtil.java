package edu.elon.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.elon.contact.Contact;

public class DBUtil {
	
	// Write a private method to not re-write code!!!
	
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
		String query = "UPDATE contact_db.contacts " + "SET first_name='" + c.getfName() + "'," + "middle_name='"
				+ c.getfName() + "'," + "last_name='" + c.getfName() + "'," + "email='" + c.getfName() + "',"
				+ "major='" + c.getfName() + "',";
	}

	public static ArrayList<Contact> getContacts(String username, String password, String url, String IPAddress,
			String DBName, String TableName) {
		String query = "SELECT * FROM contact_db.contacts";

		Connection c = null;
		Statement stmt = null;
		ResultSet r = null;

		ArrayList<Contact> result = new ArrayList<Contact>();
		try {
			c = DriverManager.getConnection(url, username, password);
			stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			r = stmt.executeQuery(query);

			while (r.next()) {
				result.add(new Contact(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(4),
						r.getString(5)));
			}

		} catch (SQLException e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				if (c != null) {
					c.close();
					System.out.println("Connection Closed");
				}
				if (stmt != null) {
					stmt.close();
					System.out.println("Stmt Closed");
				}
				if (r != null) {
					r.close();
					System.out.println("Result Set Closed");
				}
			} catch (SQLException e) {
				System.out.println("Exception: " + e);
			}
		}
		return result;
	}

}
