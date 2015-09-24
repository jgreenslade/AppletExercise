/**
 * Copyright (c) 2015, Jacopo Greenslade
 */
package edu.elon.math;

import java.util.ArrayList;

/**
 * Function class that calculates the Minimum Absolute Sum of the inputs
 * @author jgreenslade
 *
 */
public class MinimumAbsoluteSum extends Function {

	/**
	 * Standard no argument constructor. Sets the initial values
	 */
	public MinimumAbsoluteSum() {
		this(new double[] { -100, 100,-100, 100,-100, 100,-100, 100,-100, 100 });
	}
	/**
	 * Constructor takes ArrayList<Double> of Double values
	 * @param inputs, ArrayList of inputs
	 */
	public MinimumAbsoluteSum(ArrayList<Double> inputs) {
		this(inputs, createDefaultInputNames());
	}
	/**
	 * Constructor takes ArrayList<Double> of Double values and ArrayList<String> of names
	 * @param values, ArrayList of inputs
	 * @param names, ArrayList of names
	 */
	public MinimumAbsoluteSum(ArrayList<Double> values, ArrayList<String> names) {
		this.setInputValues(values);
		this.setInputNames(names);
		this.setMinimize(true);
		this.setTitle("Minimum Absolute Sum");
	}
	/**
	 * Constructor takes array of double as inputs
	 * @param inputs, double[] of inputs
	 */
	public MinimumAbsoluteSum(double[] inputs) {
		ArrayList<Double> values = new ArrayList<Double>();
		for (double d : inputs) {
			values.add(new Double(d));
		}
		this.setInputValues(values);
		this.setInputNames(createDefaultInputNames());
		this.setMinimize(true);
		this.setTitle("Minimum Absolute Sum");
	}

	private static ArrayList<String> createDefaultInputNames() {
		ArrayList<String> names = new ArrayList<String>();
		for (int i = 1; i <= 10; i++) {
			names.add("Value " + i);
		}
		return names;
	}
	/**
	 * Calculates Minimum Absolute Sum based on current values
	 */
	@Override
	public Double evaluate() {
		Double result = 0.0;
		for (Double value: this.getInputValues()){
			result += Math.abs(value);
		}
		this.setOutput(new Double(result));
		return result;
	}

}
