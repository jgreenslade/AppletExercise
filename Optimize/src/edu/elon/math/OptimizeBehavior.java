/**
 * Copyright (c) 2015, Jacopo Greenslade
 */
package edu.elon.math;

/**
 * Optimization Behavior interface for the Function class' Strategy
 * pattern
 * 
 * @author jgreenslade
 *
 */
public interface OptimizeBehavior {

  /**
   * Calls the optimization method of the optimizer and returns the
   * final value
   * 
   * @param f, the function used to find the optimmal value 
   * @return,
   *          final optimized value
   */
  public Double optimize(Function f);

}