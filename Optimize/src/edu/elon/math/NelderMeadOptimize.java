/**
 * Copyright (c) 2015, Jacopo Greenslade
 */
package edu.elon.math;

/**
 * Optimization Behavior for the Function class' Strategy pattern using NelderMead
 * @author jgreenslade
 *
 */
public class NelderMeadOptimize implements OptimizeBehavior{

	@Override
	public Double optimize(Function function) {
		Double optimalValue = null;
		NelderMead nm = new NelderMead();
		optimalValue = nm.goSimplex(function);
		function.getInputValues();
		return optimalValue;
	}

}
