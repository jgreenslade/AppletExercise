/**
 * Copyright (c) 2015, Jacopo Greenslade
 */

package edu.elon.math;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Creates a gui based on the Functions inputs and names
 * 
 * @author jgreenslade
 *
 */
public class DinamicGui extends JFrame implements Observer {

  private Function f;
  private JTextField[] fields;
  private ArrayList<String> inputNames;
  private int inputs = 5;
  private JButton optimize;
  private JTextField result;
  private JButton solve;

  private ArrayList<Double> values;

  /**
   * Constructor that receives a function and calls helper methods to
   * build the gui correctly
   * 
   * @param function
   */
  public DinamicGui(Function function) {
    this.f = function;
    f.addObserver(this);
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

  /**
   * Update is part of the Observer Pattern
   */
  @Override
  public void update(Observable o, Object arg) {
    Function function = (Function) o;
    updateInputs(function);
    updateOutput(function);
    // updateInputs(f);
    // updateOutput(f);
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
      updateInputs(f);
      // container.removeAll();
      // container.add(select, BorderLayout.NORTH);
      // container.add(makeFields(), BorderLayout.CENTER);
      // container.add(bottomPanel, BorderLayout.SOUTH);
      // updateInputs(f);
      // container.repaint();
      // container.revalidate();
    });
    optimize.addActionListener(e -> {

      f.setInputValues(getValues()); // This fixes it for the moment
                                     // FIXED

      container.removeAll();
      container.add(select, BorderLayout.NORTH);
      container.add(makeFields(), BorderLayout.CENTER);
      container.add(bottomPanel, BorderLayout.SOUTH);

      // Reflection sets the right optimizer
      String className = select.getSelectedItem() + "";
      try {
        // Class<?> c1 = Class.forName(className);
        // //System.out.println(c1);
        // OptimizeBehavior o = (OptimizeBehavior) c1.newInstance();

        f.setOptimizeBehavior(
                OptimizerFactory.makeInstance().createOptimizer(className));
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
      container.repaint(); // Job of Optimizer
      container.revalidate(); // Fix
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
    resultPanel.add(result = new JTextField("0", 15), BorderLayout.EAST);

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

  private void updateInputs(Function function) {
    ArrayList<Double> inputValues = function.getInputValues();
    for (int i = 0; i < inputValues.size(); i++) {
      fields[i].setText(inputValues.get(i) + "");
    }

  }

  private void updateOutput(Function function) {
    result.setText(function.getOutput() + "");
  }
}
