package edu.elon.util;
/**
 * (c) Copyright 2015, Jacopo Greenslade
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.elon.contact.Contact;
import edu.elon.contact.Gui;

/**
 * Utility class that interacts with the Database through a JDBC connection
 * @author jgreenslade
 *
 */
public class DBUtil {
	private static String url;
	private static String username;
	private static String password;
	private static String dBName;
	private static String tableName;

	
	/**
	 * Adds a contact to DB based on user input
	 * @param contact
	 */
	public static void insertContact(Contact contact) {
		String query = "INSERT INTO contact_db.contacts (`first_name`, `middle_name`, `last_name`, `email_address`, `major`)"
				+ "VALUES('" + contact.getfName() + "','" + contact.getmName() + "','" + contact.getlName() + "','"
				+ contact.geteMail() + "','" + contact.getMajor() + "')";
		Connection c = null;
		Statement stmt = null;
		ResultSet r = null;

		try {
			c = DriverManager.getConnection(url, username, password);
			stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println("Exception: " + e);
		} finally {
			close(c, stmt, r);
		}
	}

	/**
	 * Removes an item from DB based on its index
	 * @param id, int value of id of item to remove
	 */
	public static void removeContact(int id) {
		String query = "DELETE from " + dBName + "." + tableName + " WHERE contacts.index = " + id + "";
		Connection c = null;
		Statement stmt = null;
		ResultSet r = null;

		try {
			c = DriverManager.getConnection(url, username, password);
			stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println("Exception: " + e);
		} finally {
			close(c, stmt, r);
		}
	}

	/**
	 * Clears DB
	 */
	public static void removeAll() {
		String query = "TRUNCATE table " + dBName + "." + tableName;
		Connection c = null;
		Statement stmt = null;
		ResultSet r = null;

		try {
			c = DriverManager.getConnection(url, username, password);
			stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println("Exception: " + e);
		} finally {
			close(c, stmt, r);
		}
	}

	/**
	 * Updates a DB entry based on user input
	 * @param contact, Contact info to update
	 */
	public static void updateContact(Contact contact) {
		String query = "UPDATE contact_db.contacts " + "SET contacts.index=" + contact.getIndex() + ", first_name='"
				+ contact.getfName() + "'," + "middle_name='" + contact.getmName() + "'," + "last_name='"
				+ contact.getlName() + "'," + "email_address='" + contact.geteMail() + "'," + "major='"
				+ contact.getMajor() + "' WHERE contacts.index = " + contact.getIndex();
		Connection c = null;
		Statement stmt = null;
		ResultSet r = null;

		try {
			c = DriverManager.getConnection(url, username, password);
			stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println("Exception: " + e);
		} finally {
			close(c, stmt, r);
		}
	}

	/**
	 * Sets the parameters for DB connection
	 * @param aUsername, String value for DB username
	 * @param aPassword, String value for DB password
	 * @param aUrl, String value for DB url
	 * @param aDBName, String value for DB Name
	 * @param aTableName, String value for DB Table
	 */
	public static void setDBParameters(String aUsername, String aPassword, String aUrl, String aDBName,
			String aTableName) {
		username = aUsername;
		password = aPassword;
		url = aUrl;
		dBName = aDBName;
		tableName = aTableName;
	}

	
	/**
	 * Gets all Contacts from DB and returns them as an ArrayList 
	 * @return, ArrayList of type Contact 
	 */
	public static ArrayList<Contact> getContacts() {
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
				result.add(new Contact(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5),
						r.getString(6)));
			}

		} catch (SQLException e) {
			Gui.showErrorDialog();
		} finally {
			close(c, stmt, r);
		}
		return result;
	}

	private static void close(Connection c, Statement stmt, ResultSet r) {
		try {
			if (c != null) {
				c.close();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connection Closed");
	}

}
