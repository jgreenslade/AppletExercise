/**
 * Copyright (c) 2015, Jacopo Greenslade
 */
package edu.elon.math;

/**
 * Optimization Behavior for the Function class' Strategy pattern
 * @author jgreenslade
 *
 */
public class RandomWalkOptimize implements OptimizeBehavior{

	@Override
	public Double optimize(Function function) {
		Double optimalValue = null;
		RandomWalk rw = new RandomWalk();
		optimalValue = rw.guess(function);
		function.getInputValues();
		return optimalValue;
	}

}
