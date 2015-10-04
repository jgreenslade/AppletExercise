package edu.elon.math;

public class OptimizerFactory {

  private static OptimizerFactory factory;

  private OptimizerFactory() {

  }

  /**
   * 
   * Returns the singleton instance of OptimizerFactory if it exists
   * or null if it doesn't
   * 
   * @return OptimizerFactory instance
   */
  public static synchronized OptimizerFactory makeInstance() {
    if (factory == null) {
      factory = new OptimizerFactory();
    }
    return factory;
  }

  /**
   * Creates an instance of one of the optimizers based on a String
   * value
   * 
   * @param name, name of OptimizeBehavior to create 
   * @return, instance
   *          of OptimizeBehavior
   */
  public OptimizeBehavior createOptimizer(String name) {
    try {
      Class<?> c1 = Class.forName(name);
      // System.out.println(c1);
      OptimizeBehavior o = (OptimizeBehavior) c1.newInstance();
      return o;
    } catch (Exception e1) {
      System.out.println(e1);
    }
    return null;
  }

}
