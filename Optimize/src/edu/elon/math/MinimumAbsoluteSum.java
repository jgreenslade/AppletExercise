package edu.elon.math;

import java.util.ArrayList;

public class MinimumAbsoluteSum extends Function {

	public MinimumAbsoluteSum() {
		this(new double[] { -100, 100,-100, 100,-100, 100,-100, 100,-100, 100 });
	}

	public MinimumAbsoluteSum(ArrayList<Double> inputs) {
		this(inputs, createDefaultInputNames());
	}

	public MinimumAbsoluteSum(ArrayList<Double> values, ArrayList<String> names) {
		this.setInputValues(values);
		this.setInputNames(names);
		this.setMinimize(true);
		this.setTitle("Minimum Absolute Sum");
	}

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
