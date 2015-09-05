package edu.elon.contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Creates a connection to a DB
 * @author jgreenslade
 *
 */
public class DBConnection {

	public static final String USERNAME = "root";
	public static final String PASSWORD = "mysqluser";
	public static final String URL = "jdbc:mysql://localhost";
	
	private Connection c;
	
	public DBConnection() {

	}

	public Statement connect() {
		c = null;
		Statement stmt = null;

		try {
			c = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Connected!");

			stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
					System.out.println("Closed");
				}
			} catch (SQLException e) {
				System.out.println("Exception: " + e);
			}
		}
		return stmt;

	}
	public void close() {
		try {
			if (c != null) {
				c.close();
				System.out.println("Closed");
			}
		} catch (SQLException e) {
			System.out.println("Exception: " + e);
		}
	}
}
