/**
 * (c) Copyright 2015, Jacopo Greenslade
 */

package edu.elon.contact;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import javax.swing.*;

import edu.elon.util.DBUtil;

import java.awt.*;

public class Gui {

	public static final int FRAME_WIDTH = 300;
	public static final int FRAME_HEIGHT = 250;
	public static final String DEFAULT_USER = "root";
	public static final String DEFAULT_PSWD = "mysqluser";
	public static final String DEFAULT_IP = "jdbc:mysql://localhost:3306";
	public static final String DEFAULT_DBNAME = "contact_db";
	public static final String DEFAULT_TABLE = "contacts";

	private JFrame frame;
	private SpringLayout spring;
	private JMenuBar menuBar;
	private JMenu m_file, m_edit;
	private JMenuItem mi_clearDB, mi_connect, mi_exit, mi_add, mi_update, mi_remove;
	private JButton b_previous, b_next, b_ok, b_cancel;

	private String[] labels = null;
	private JTextField[] textFields = new JTextField[5];

	private ContactController controller;

	/**
	 * Standard Empty constructor
	 */
	public Gui(ContactController aController) {
		controller = aController;
	}

	/**
	 * 
	 */
	public void makeGUI() {
		frame = new JFrame("Title Goes Here");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up layout
		Container contentPane = frame.getContentPane();
		spring = new SpringLayout();
		contentPane.setLayout(spring);

		setUpMenus();
		setUpButtons();
		// Create and populate the panel
		JPanel p = new JPanel(new SpringLayout());

		String[] contactLabels = { "First Name", "Middle Name", "Last Name", "Email", "Major" };

		labels = contactLabels;
		// Lay out the panel.
		makePanel(p);
		makeButtonSubPanel(p, 0);
		makeGrid(p);
		frame.setContentPane(p);
		menuBar.add(m_file);
		menuBar.add(m_edit);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setJMenuBar(menuBar);

	}

	/**
	 * Get a contact from the contact list based on your current position in it
	 * 
	 * @param c
	 * @param position
	 */
	public void getContactInfo(Contact c) {

		if (c == null) {
			return;
		}
		textFields[0].setText(c.getfName());
		textFields[1].setText(c.getmName());
		textFields[2].setText(c.getlName());
		textFields[3].setText(c.geteMail());
		textFields[4].setText(c.getMajor());
		frame.repaint();
	}

	/**
	 * 
	 */
	public static void showErrorDialog() {
		JOptionPane.showMessageDialog(null, "You did not correctly speciffy db parameters", "DB Settings",
				JOptionPane.ERROR_MESSAGE);
	}

	private void setUpMenus() {
		// Set up menuBar
		menuBar = new JMenuBar();

		// Set up menu
		m_file = new JMenu("File");
		m_edit = new JMenu("Edit");

		// Set up menuItems
		mi_exit = new JMenuItem("Exit");
		mi_connect = new JMenuItem("Connect");
		mi_clearDB = new JMenuItem("Clear Database");
		mi_add = new JMenuItem("Add");
		mi_update = new JMenuItem("Update");
		mi_remove = new JMenuItem("Remove");

		// Add listeners
		mi_exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mi_connect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Take input info and make connection to DB
				String[] connectDBLabels = { "User Name", "Password", "IPAddress", "Database Name", "Table Name" };
				labels = connectDBLabels;
				JPanel p = new JPanel(new SpringLayout());
				setOkListener(0);
				makePanel(p);
				makeButtonSubPanel(p, -1); // Connect
				makeGrid(p);
				setArrayTextField(DEFAULT_USER, 0);
				setArrayTextField(DEFAULT_PSWD, 1);
				setArrayTextField(DEFAULT_IP, 2);
				setArrayTextField(DEFAULT_DBNAME, 3);
				setArrayTextField(DEFAULT_TABLE, 4);
				frame.setContentPane(p);
				frame.setVisible(true);
				frame.repaint();
				frame.revalidate();
			}
		});
		mi_clearDB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Call DBUtil Clear
				DBUtil.removeAll();
				mi_clearDB.setEnabled(false);
				mi_remove.setEnabled(false);
				mi_add.setEnabled(false);
				mi_update.setEnabled(false);
				controller.setContacts(new ArrayList<Contact>());
				for (JTextField t : textFields) {
					t.setText("");
				}
			}
		});
		mi_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setOkListener(1);
				JPanel p = new JPanel(new SpringLayout());
				String[] contactLabels = { "First Name", "Middle Name", "Last Name", "Email", "Major" };
				labels = contactLabels;
				setOkListener(0);
				makePanel(p);
				makeButtonSubPanel(p, 1);
				makeGrid(p);
				frame.setContentPane(p);
				frame.setVisible(true);
				frame.repaint();
				frame.revalidate();
			}
		});
		mi_update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Update  ()");
				int index = controller.getActiveContact();
				System.out.println(index);

				DBUtil.updateContact(new Contact(index + 1, textFields[0].getText(), textFields[1].getText(),
						textFields[2].getText(), textFields[3].getText(), textFields[4].getText()));

				controller.setContacts(DBUtil.getContacts());
				controller.refreshGui();
			}
		});
		mi_remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Remove");
				DBUtil.removeContact(controller.getActiveContact() + 1);
				controller.setContacts(DBUtil.getContacts());
				controller.refreshGui();
			}
		});

		mi_clearDB.setEnabled(false);
		mi_remove.setEnabled(false);
		mi_add.setEnabled(false);
		mi_update.setEnabled(false);

		m_file.add(mi_clearDB);
		m_file.add(mi_connect);
		m_file.addSeparator();
		m_file.add(mi_exit);
		m_edit.add(mi_add);
		m_edit.add(mi_update);
		m_edit.add(mi_remove);

	}

	private void setArrayTextField(String t, int i) {
		textFields[i].setText(t);
	}

	private void setOkListener(int i) {
		// First make sure there are no listeners
		for (ActionListener a : b_ok.getActionListeners()) {
			b_ok.removeActionListener(a);
		}
		if (i == 0) {
			b_ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Connect Screen
					System.out.println("Connect  A----B");
					DBUtil.setDBParameters(textFields[0].getText(), textFields[1].getText(), textFields[2].getText(),
							textFields[3].getText(), textFields[4].getText());

					controller.setContacts(DBUtil.getContacts());
					// controller.refreshGui();
					JPanel p = new JPanel(new SpringLayout());
					String[] contactLabels = { "First Name", "Middle Name", "Last Name", "Email", "Major" };
					labels = contactLabels;
					makePanel(p);
					makeButtonSubPanel(p, 0);
					makeGrid(p);
					frame.setContentPane(p);

					mi_clearDB.setEnabled(true);
					mi_remove.setEnabled(true);
					mi_add.setEnabled(true);
					mi_update.setEnabled(true);

					frame.setVisible(true);
					frame.repaint();

					controller.refreshGui();
				}
			});
		} else if (i == 1) {
			b_ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Add Screen
					System.out.println("Add  +++");
					DBUtil.insertContact(new Contact(0, textFields[0].getText(), textFields[1].getText(),
							textFields[2].getText(), textFields[3].getText(), textFields[4].getText()));
					JPanel p = new JPanel(new SpringLayout());
					makePanel(p);
					makeButtonSubPanel(p, 0);
					makeGrid(p);
					frame.setContentPane(p);
					frame.setVisible(true);
					frame.repaint();
					controller.setContacts(DBUtil.getContacts());
					controller.refreshGui();
				}
			});
		}
	}

	private void setUpButtons() {
		b_previous = new JButton("Previous");
		b_next = new JButton("Next");
		b_ok = new JButton("OK");
		b_cancel = new JButton("Cancel");

		// Listeners
		b_previous.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Behavior
				System.out.println("<-- Previous");
				controller.setActiveContact(controller.getActiveContact() - 1);
				controller.refreshGui();
			}
		});
		b_next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Behavior
				System.out.println("Next  -->");
				controller.setActiveContact(controller.getActiveContact() + 1);
				controller.refreshGui();
			}
		});
		b_cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Behavior
				System.out.println("Cancel  xxx");
				JPanel p = new JPanel(new SpringLayout());
				String[] contactLabels = { "First Name", "Middle Name", "Last Name", "Email", "Major" };
				labels = contactLabels;
				makePanel(p);
				makeButtonSubPanel(p, 0);
				makeGrid(p);
				frame.setContentPane(p);
				frame.setVisible(true);
				frame.repaint();
				controller.refreshGui();
			}
		});
	}

	private void makePanel(JPanel p) {

		for (int i = 0; i < labels.length; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			p.add(l);
			textFields[i] = new JTextField(10);
			l.setLabelFor(textFields[i]);
			p.add(textFields[i]);
		}
	}

	private void makeButtonSubPanel(JPanel p, int type) {
		p.add(Box.createRigidArea(new Dimension(5, 5)));
		JPanel p2 = new JPanel();
		if (type == 1) { // Add screen (OK and Cancel)
			setOkListener(1); // OK Add
			p2.add(b_ok);
			p2.add(b_cancel);
			p.add(p2);
		} else if (type == -1) { // Connect Screen (OK)
			setOkListener(0); // OK connect
			p2.add(b_ok);
			p.add(p2);
		} else if (type == 0) { // Main Screen (Previous and Next)
			p2.add(b_previous);
			p2.add(b_next);
			p.add(p2);
		}
	}

	private void makeGrid(JPanel p) {
		SpringUtilities.makeCompactGrid(p, labels.length + 1, 2, 6, 6, 6, 6);
	}

}
