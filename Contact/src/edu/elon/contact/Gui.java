package edu.elon.contact;

import java.awt.Container;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

public class Gui {

	public static final int FRAME_WIDTH = 300;
	public static final int FRAME_HEIGHT = 250;

	private JFrame frame;
	private SpringLayout spring;
	private JMenuBar menuBar;
	private JMenu m_file, m_edit;
	private JMenuItem mi_clearDB, mi_connect, mi_exit, mi_add, mi_update, mi_remove;
	private JTextField tf_fName, tf_mName, tf_lName, tf_eMail, tf_major, tf_userName, tf_password, tf_IPAddress,
			tf_DBName, tf_TableName;
	private JButton b_previous, b_next;

	// Arrays
	// private String[] labels = { "First Name", "Middle Name", "Last Name",
	// "Email", "Major" };
	// private JTextField[] textFields = { tf_fName, tf_mName, tf_lName,
	// tf_eMail, tf_major };

	private String[] labels = { "User Name", "Password", "IPAddress", "Database Name", "Table Name" };
	private JTextField[] textFields = { tf_userName, tf_password, tf_IPAddress, tf_DBName, tf_TableName };

	/**
	 * Standard Empty constructor
	 */
	public Gui() {
		frame = new JFrame("Title Goes Here");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up layout
		Container contentPane = frame.getContentPane();
		spring = new SpringLayout();
		contentPane.setLayout(spring);

		setUpMenus();

		// Create and populate the panel
		JPanel p = new JPanel(new SpringLayout());

		// Lay out the panel.
		makePanel(p);

		frame.setContentPane(p);

		menuBar.add(m_file);
		menuBar.add(m_edit);

		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);

		frame.setJMenuBar(menuBar);
	}

	private void setUpMenus() {
		// Set up menuBar
		menuBar = new JMenuBar();

		// Set up menu
		m_file = new JMenu("File");
		m_edit = new JMenu("Edit");

		// Set up menuItems
		mi_exit = new JMenuItem("Exit");
		mi_connect = new JMenuItem("Great");
		mi_clearDB = new JMenuItem("Clear Database");
		mi_add = new JMenuItem("Add");
		mi_update = new JMenuItem("Update");
		mi_remove = new JMenuItem("Remove");

		m_file.add(mi_clearDB);
		m_file.add(mi_exit);
		m_file.add(mi_connect);
		m_edit.add(mi_add);
		m_edit.add(mi_update);
		m_edit.add(mi_remove);

	}

	/**
	 * Get a contact from the contact list based on your current position in it
	 * 
	 * @param c
	 * @param position
	 */
	public void getContactInfo(ArrayList<Contact> c, int position) {
		Contact con = c.get(position);
		textFields[0].setText(con.getfName());
		textFields[1].setText(con.getmName());
		textFields[2].setText(con.getlName());
		textFields[3].setText(con.geteMail());
		textFields[4].setText(con.getMajor());
		frame.repaint();
	}

	private void makePanel(JPanel p) {

		for (int i = 0; i < labels.length; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			p.add(l);
			textFields[i] = new JTextField(10);
			l.setLabelFor(textFields[i]);
			p.add(textFields[i]);
		}

		b_previous = new JButton("Previous");
		b_next = new JButton("Next");

		p.add(Box.createRigidArea(new Dimension(5, 5)));
		JPanel p2 = new JPanel();
		p2.add(b_previous);
		p2.add(b_next);
		p.add(p2);

		SpringUtilities.makeCompactGrid(p, labels.length + 1, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad
	}

	public void showErrorDialog() {
		JOptionPane.showMessageDialog(frame, "You did not correctly speciffy db parameters", "DB Settings",
				JOptionPane.ERROR_MESSAGE);

	}

}
