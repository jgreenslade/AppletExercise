/**
 * Copyright (c) 2015, Jacopo Greenslade
 */
package edu.elon.math;

/**
 * Optimization Behavior for the Function class' Strategy pattern
 * @author jgreenslade
 *
 */
public class PowellOptimize implements OptimizeBehavior {

	@Override
	public Double optimize(Function function) {
		Double optimalValue = null;
		Powell powell = new Powell();
		optimalValue = powell.findMinimum(function);
		function.getInputValues();
		return optimalValue;
	}
}
