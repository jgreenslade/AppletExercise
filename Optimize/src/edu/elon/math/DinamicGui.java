package edu.elon.math;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class DinamicGui extends JFrame{
	
	private JTextField[] fields;
	
	 // Get from function
	private int inputs = 5;
	private String title = "To Be Determined";
	
    private JButton solve;
    private JButton optimize;
    private JTextField result;
	
	public DinamicGui() {
		// TODO Get title from which function
		this.setTitle(title);
	    createGui();
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.pack();
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	}

	private void createGui() {
		String[] optimizers = {"NelderMead", "Powell", "RandomWalk"};
		
		Container container = this.getContentPane();
	    JPanel bottomPanel;
	    
	    JComboBox<String> select = new JComboBox<String>(optimizers);
	    
	    container.add(select, BorderLayout.NORTH);
	    container.add(makeFields(inputs), BorderLayout.CENTER);
	    container.add(bottomPanel = makeBottomPanel(), BorderLayout.SOUTH);
	    
	    solve.addActionListener(e -> {
	    	System.out.println("Solve");
	    	container.removeAll();
	    	container.add(select, BorderLayout.NORTH);
	    	container.add(makeFields(++inputs), BorderLayout.CENTER);
	    	container.add(bottomPanel, BorderLayout.SOUTH);
	    	container.repaint();
	    	container.revalidate();
	    });
	    optimize.addActionListener(e -> {
	    	System.out.println("Optimize");
	    	container.removeAll();
	    	container.add(select, BorderLayout.NORTH);
	    	container.add(makeFields(--inputs), BorderLayout.CENTER);
	    	container.add(bottomPanel, BorderLayout.SOUTH);
	    	container.repaint();
	    	container.revalidate();
	    });
	}

	private JPanel makeBottomPanel() {
		JPanel resultPanel = new JPanel();
	    JPanel buttonPanel = new JPanel();
	    JPanel bottomPanel = new JPanel();
	    
	    resultPanel.add(new JLabel("Result: "), BorderLayout.WEST);
	    resultPanel.add(result = new JTextField("0", 10), BorderLayout.EAST);
	    
	    buttonPanel.add(solve = new JButton("Solve"), BorderLayout.WEST);
	    buttonPanel.add(optimize = new JButton("Optimize"), BorderLayout.EAST);
	    
	    bottomPanel.setLayout(new GridLayout(2, 1));
	    bottomPanel.add(resultPanel);
	    bottomPanel.add(buttonPanel);
	    
	    return bottomPanel;
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
