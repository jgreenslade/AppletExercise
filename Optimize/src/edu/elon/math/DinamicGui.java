/**
 * Copyright (c) 2015, Jacopo Greenslade
 */

package edu.elon.math;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

/**
 * Creates a gui based on the Functions inputs and names
 * @author jgreenslade
 *
 */
public class DinamicGui extends JFrame implements Observer {

	private JTextField[] fields;
	private int inputs = 5;
	private ArrayList<String> inputNames;
	private ArrayList<Double> values;
	private JButton solve;
	private JButton optimize;
	private JTextField result;

	private Function f;
	
	/**
	 * Constructor that receives a function and calls helper methods to build the gui correctly
	 * @param function
	 */
	public DinamicGui(Function function) {
		this.f = function;
		f.addObserver((Observer) this);
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

		String[] optimizers = getOptimizers();

		Container container = this.getContentPane();
		JPanel bottomPanel;

		JComboBox<String> select = new JComboBox<String>(optimizers);

		container.add(select, BorderLayout.NORTH);
		container.add(makeFields(), BorderLayout.CENTER);
		container.add(bottomPanel = makeBottomPanel(), BorderLayout.SOUTH);

		solve.addActionListener(e -> {
			f.setInputValues(getValues());
			f.evaluate();
			updateOutput(f);
			container.removeAll();
			container.add(select, BorderLayout.NORTH);
			container.add(makeFields(), BorderLayout.CENTER);
			container.add(bottomPanel, BorderLayout.SOUTH);
			updateInputs(f);
			container.repaint();
			container.revalidate();
		});
		optimize.addActionListener(e -> {

			// System.out.println("Optimize");
			container.removeAll();
			container.add(select, BorderLayout.NORTH);
			container.add(makeFields(), BorderLayout.CENTER);
			container.add(bottomPanel, BorderLayout.SOUTH);

			// Reflection sets the right optimizer
			String className = select.getSelectedItem() + "Optimize";
			try {
				Class<?> c1 = Class.forName(className);
				System.out.println(c1);
				OptimizeBehavior o = (OptimizeBehavior) c1.cast(c1.newInstance());
				f.setOptimizeBehavior(o);
			} catch (Exception e1) {
				System.out.println(e);
			}
			class ListenerThread extends Thread {

				@Override
				public void run() {
					f.performOptimizeBehavior();
				}

			}
			ListenerThread l = new ListenerThread();
			l.start();
			container.repaint();
			container.revalidate();
		});
		
	}

	private String[] getOptimizers() {
		String path = "";
		try {
			path = System.getenv("optimizers");
			String[] directories = path.split(";");
			return directories;
		} catch (NullPointerException e) {
			System.out.println("Environment Variable not Defined!");
		}

		return null;

	}

	private ArrayList<Double> getValues() {
		ArrayList<Double> result = new ArrayList<Double>();
		for (JTextField t : fields) {
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
			rightGrid.add(fields[i] = new JTextField(values.get(i) + "", 10)); 
		}

		holdGrid.setLayout(new BorderLayout(5, 0));
		holdGrid.add(leftGrid, BorderLayout.WEST);
		holdGrid.add(rightGrid, BorderLayout.CENTER);

		return new JScrollPane(holdGrid);
	}
	
	/**
	 * Update is part of the Observer Pattern
	 */
	@Override
	public void update(Observable o, Object arg) {
		Function function = (Function) o;
		updateInputs(function);
		updateOutput(function);
	}

	private void updateOutput(Function function) {
		result.setText(function.getOutput() + "");
	}

	private void updateInputs(Function function) {
		ArrayList<Double> inputValues = function.getInputValues();
		for (int i = 0; i < inputValues.size(); i++) {
			fields[i].setText(inputValues.get(i) + "");
		}

	}
}
