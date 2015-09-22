package edu.elon.math;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class DinamicGui extends JFrame implements Observer{
	
	private JTextField[] fields;
	private int inputs = 5;
	private ArrayList<String> inputNames;
	private ArrayList<Double> values;
    private JButton solve;
    private JButton optimize;
    private JTextField result;
	
    private Function f;
    
	public DinamicGui(Function function) {
		this.f = function;
		this.setTitle(f.getTitle());
		inputNames = f.getInputNames();
		values = f.getInputValues();
		inputs = inputNames.size();
	    createGui();
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.pack();
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	}

	private void createGui() {
		String[] optimizers = {"edu.elon.math.NelderMead", "edu.elon.math.Powell", "edu.elon.math.RandomWalk"};
		
		Container container = this.getContentPane();
	    JPanel bottomPanel;
	    
	    JComboBox<String> select = new JComboBox<String>(optimizers);
	    
	    select.setSelectedIndex(0);
	    select.addActionListener(e -> {
	    	System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	    });
	    
	    container.add(select, BorderLayout.NORTH);
	    container.add(makeFields(), BorderLayout.CENTER);
	    container.add(bottomPanel = makeBottomPanel(), BorderLayout.SOUTH);
	    
	    solve.addActionListener(e -> {
	    	System.out.println("Solve");
	    	container.removeAll();
	    	f.setInputValues(getValues());
	    	f.evaluate();
	    	updateOutput(f);
	    	container.add(select, BorderLayout.NORTH);
	    	container.add(makeFields(), BorderLayout.CENTER);
	    	container.add(bottomPanel, BorderLayout.SOUTH);
	    	container.repaint();
	    	container.revalidate();
	    });
	    optimize.addActionListener(e -> {
	    	System.out.println("Optimize");
	    	container.removeAll();
	    	container.add(select, BorderLayout.NORTH);
	    	container.add(makeFields(), BorderLayout.CENTER);
	    	container.add(bottomPanel, BorderLayout.SOUTH);
	    	f.performOptimizeBehavior();
	    	updateOutput(f);
	    	container.repaint();
	    	container.revalidate();
	    });
	}

	private ArrayList<Double> getValues() {
		ArrayList<Double> result = new ArrayList<Double>();
		for (JTextField t: fields) {
			result.add(Double.parseDouble(t.getText()));
		}
		return result;
	}

	private JPanel makeBottomPanel() {
		JPanel resultPanel = new JPanel();
	    JPanel buttonPanel = new JPanel();
	    JPanel bottomPanel = new JPanel();
	    
	    resultPanel.add(new JLabel("Result: "), BorderLayout.WEST);
	    resultPanel.add(result = new JTextField("0"), BorderLayout.EAST);
	    
	    buttonPanel.add(solve = new JButton("Solve"), BorderLayout.WEST);
	    buttonPanel.add(optimize = new JButton("Optimize"), BorderLayout.EAST);
	    
	    bottomPanel.setLayout(new GridLayout(2, 1));
	    bottomPanel.add(resultPanel);
	    bottomPanel.add(buttonPanel);
	    
	    return bottomPanel;
	}

	private JScrollPane makeFields() {
		
		fields = new JTextField[inputs];
		
		JPanel holdGrid = new JPanel();
	    JPanel leftGrid = new JPanel();
	    JPanel rightGrid = new JPanel();
	    
	    leftGrid.setLayout(new GridLayout(inputs, 1));
	    rightGrid.setLayout(new GridLayout(inputs, 1));
	    
		for (int i = 0; i < inputs; i++) {
		    leftGrid.add(new JLabel(inputNames.get(i), SwingConstants.RIGHT));
		    rightGrid.add(fields[i] = new JTextField(values.get(i) + "", 10)); // Put the text fields in an array to access them later
		}
		
		holdGrid.setLayout(new BorderLayout(5, 0));
	    holdGrid.add(leftGrid, BorderLayout.WEST);
	    holdGrid.add(rightGrid, BorderLayout.CENTER);
		
	    return new JScrollPane(holdGrid);
	}

	@Override
	public void update(Observable o, Object arg) {
		updateInputs((Function) o);
		updateOutput((Function) o);
	}

	private void updateOutput(Function o) {
		result.setText(o.getOutput() + "");
	}

	private void updateInputs(Function o) {
		ArrayList<Double> inputValues = o.getInputValues();
		for (int i = 0; i < inputValues.size(); i++) {
			fields[i].setText(inputValues.get(i) + "");
		}
		
	}
}
