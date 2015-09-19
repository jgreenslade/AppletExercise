package edu.elon.math;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class DinamicGui extends JFrame{
	
	private JTextField[] fields;
	
	public DinamicGui() {
		this.setTitle("Control GUI");
	    //this.weatherData = weatherData;
	    createGui();
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.pack();
	    this.setVisible(true);
	}

	private void createGui() {
		Container container = this.getContentPane();

	    JPanel buttonPanel = new JPanel();
	    JComboBox selectOptimizerPanel = new JComboBox();
	    
	    JButton update;
	    JComboBox select;
	    
	    String[] optimizers = {"NelderMead", "Powell", "RandomWalk"};
	    
	    buttonPanel.add(update = new JButton("Update"));
	    selectOptimizerPanel.add(select = new JComboBox<String>(optimizers));
	    
	    container.add(selectOptimizerPanel, BorderLayout.NORTH);
	    container.add(makeFields(2), BorderLayout.CENTER);
	    container.add(buttonPanel, BorderLayout.SOUTH);
	    
	    update.addActionListener(e -> {
	    	container.removeAll();
	    	container.add(makeFields(7), BorderLayout.CENTER);
	    	container.add(buttonPanel, BorderLayout.SOUTH);
	    	container.repaint();
	    	container.revalidate();
	    });
	}

	private JScrollPane makeFields(int n) {
		
		fields = new JTextField[n];
		
		JPanel holdGrid = new JPanel();
	    JPanel leftGrid = new JPanel();
	    JPanel rightGrid = new JPanel();
	    
	    leftGrid.setLayout(new GridLayout(n, 1));
	    rightGrid.setLayout(new GridLayout(n, 1));
	    
		for (int i = 0; i < n; i++) {
		    leftGrid.add(new JLabel("Value " + (i + 1), SwingConstants.RIGHT));
		    rightGrid.add(fields[i] = new JTextField("0", 10)); // Put the text fields in an array to access them later
		}
		
		holdGrid.setLayout(new BorderLayout(5, 0));
	    holdGrid.add(leftGrid, BorderLayout.WEST);
	    holdGrid.add(rightGrid, BorderLayout.CENTER);
		
	    return new JScrollPane(holdGrid);
	}
}
