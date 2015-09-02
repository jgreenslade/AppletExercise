package edu.elon.contact;

import java.awt.Container;

import javax.swing.*;

public class Gui {

	private JFrame frame;
	private SpringLayout spring;
	private JMenuBar menuBar;
	private JMenu m_file, m_edit;
	private JMenuItem mi_clearDB, mi_connect, mi_exit;
	private JTextField tf_fName, tf_mName, tf_lName, tf_eMail, tf_major, tf_userName, tf_password, tf_IPAddress,
			tf_DBName, tf_TableName;
	private JLabel l_fName, l_mName;

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

		// Set up menuBar
		menuBar = new JMenuBar();

		// Set up menu
		m_file = new JMenu("Menu");

		// Set up menuItems
		mi_exit = new JMenuItem("Exit");
		mi_connect = new JMenuItem("Great");

		m_file.add(mi_exit);
		m_file.add(mi_connect);

		setTextFields(); // Make sure you re-write the variables to not be
							// private

		// tf_fName = new JTextField(15); // Set size on creation
		// contentPane.add(tf_fName);
		//
		// spring.putConstraint(SpringLayout.WEST, l_fname, 5,
		// SpringLayout.WEST, contentPane);
		// spring.putConstraint(SpringLayout.NORTH, l_fname, 5,
		// SpringLayout.NORTH, contentPane);
		//
		// spring.putConstraint(SpringLayout.WEST, tf_fName, 5,
		// SpringLayout.EAST, l_fname);
		// spring.putConstraint(SpringLayout.NORTH, tf_fName, 5,
		// SpringLayout.NORTH, contentPane);

		String[] labels = { "Name: ", "Fax: ", "Email: ", "Address: " };
		int numPairs = labels.length;

		// Create and populate the panel.
		JPanel p = new JPanel(new SpringLayout());
		for (int i = 0; i < numPairs; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			p.add(l);
			JTextField textField = new JTextField(10);
			l.setLabelFor(textField);
			p.add(textField);
		}

		// Lay out the panel.

		frame.add(p);

		menuBar.add(m_file);
		frame.pack();
		frame.setSize(300, 350);
		frame.setVisible(true);
		frame.setJMenuBar(menuBar);
	}

	private void setTextFields() {

	}

	public void showFrame() {
		// TODO Auto-generated method stub

	}

	public void showErrorDialog() {
		JOptionPane.showMessageDialog(frame, "You did not correctly speciffy db parameters", "DB Settings",
				JOptionPane.ERROR_MESSAGE);

	}

}
