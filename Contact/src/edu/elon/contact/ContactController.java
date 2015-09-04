package edu.elon.contact;

public class ContactController {
	
	// Contants
	// Private variables
	private Gui gui;
	private DBConnection dbConnection;
	
	/**
	 * Standard Empty constructor
	 */
	public ContactController() {
		// Standard Empty constructor
		gui = new Gui();
	}
	/**
	 *  Starts the application
	 */
	public void go() {
		gui.showErrorDialog();
		
		dbConnection = new DBConnection();
		dbConnection.connect();
		
	}

}
