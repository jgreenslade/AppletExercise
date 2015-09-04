package edu.elon.contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	public static final String USERNAME = "root";
	public static final String PASSWORD = "mysqluser";
	public static final String URL = "jdbc:mysql://localhost";

	public DBConnection() {

	}

	public void connect() {
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			c = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Connected!");

			stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("Select * FROM contact_db.contacts");

			rs.last();
			System.out.println("Number of Rows: " + rs.getRow());

		} catch (SQLException e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				if (c != null) {
					c.close();
					System.out.println("Closed");
				}
				if (rs != null) {
					rs.close();
					System.out.println("Closed");
				}
				if (stmt != null) {
					stmt.close();
					System.out.println("Closed");
				}
			} catch (SQLException e) {
				System.out.println("Exception: " + e);
			}
		}

	}

}
