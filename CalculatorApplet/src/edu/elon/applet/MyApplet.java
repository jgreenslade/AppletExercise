package edu.elon.applet;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class MyApplet extends JApplet {

	public void init() {
		JPanel outer = new JPanel();
		JPanel buttonPanel = new JPanel(new GridLayout(4, 4));

		JButton[][] buttons = new JButton[][] {
				{ new JButton("7"), new JButton("8"), new JButton("9"), new JButton("/") },
				{ new JButton("4"), new JButton("5"), new JButton("6"), new JButton("*") },
				{ new JButton("1"), new JButton("2"), new JButton("3"), new JButton("-") },
				{ new JButton("0"), new JButton("."), new JButton("="), new JButton("+") } };

		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				buttonPanel.add(buttons[i][j]);
			}
		}
		//outer.add(buttonPanel);
		this.getContentPane().add(new JTextField(), BorderLayout.NORTH);
		this.getContentPane().add(buttonPanel);
	}
}
