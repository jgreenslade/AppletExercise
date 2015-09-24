/**
 * Copyright (c) 2015, Jacopo Greenslade
 */
package edu.elon.math;

/**
 * Optimization Behavior interface for the Function class' Strategy pattern
 * @author jgreenslade
 *
 */
public interface OptimizeBehavior {
	
	public Double optimize(Function f);

}
