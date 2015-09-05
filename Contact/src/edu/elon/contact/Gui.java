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
	private JMenuItem mi_clearDB, mi_connect, mi_exit;
	private JTextField tf_fName, tf_mName, tf_lName, tf_eMail, tf_major, tf_userName, tf_password, tf_IPAddress,
			tf_DBName, tf_TableName;
	private JButton b_previous, b_next;

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
		
		
		// _________________________ Labels and TextFields _________________________
		
		String[] labels = { "First Name", "Middle Name", "Last Name", "Email", "Major" };
		JTextField[] textFields = { tf_fName, tf_mName, tf_lName, tf_eMail, tf_major };
		
		// Create and populate the panel.
		JPanel p = new JPanel(new SpringLayout());
		// Lay out the panel.
		makePanel(labels, textFields, p);
		
		frame.setContentPane(p);
		//____________________________________________________________________________
		
		menuBar.add(m_file);
		// frame.pack();
		
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);

		frame.setJMenuBar(menuBar);
	}
	
	/**
	 * Get a contact from the contact list based on your current position in it
	 * @param c
	 * @param position
	 */
	public void getContactInfo(ArrayList<Contact> c, int position) {
		Contact con = c.get(position);
		tf_fName.setText(con.getfName());
		tf_mName.setText(con.getmName());
		tf_lName.setText(con.getlName());
		tf_eMail.setText(con.geteMail());
		tf_major.setText(con.getMajor());
		frame.repaint();
	}

	private void makePanel(String[] labels, JTextField[] textFields, JPanel p) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < labels.length; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			p.add(l);
			textFields[i] = new JTextField(10);
			l.setLabelFor(textFields[i]);
			p.add(textFields[i]);
		}
		
		b_previous = new JButton("Previous");
		b_next = new JButton("Next");
		
		p.add(Box.createRigidArea(new Dimension(5,5)));
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
