package edu.elon.math;

public class OptimizerFactory {
	
	private static OptimizerFactory factory;
	
	private OptimizerFactory() {
		
	}
	
	public OptimizeBehavior createOptimizer(String name) {
		try {
	        Class<?> c1 = Class.forName(name);
	        //System.out.println(c1);
	        OptimizeBehavior o = (OptimizeBehavior) c1.newInstance();
	        return o;
	      } catch (Exception e1) {
	        System.out.println(e1);
	      }
		return null;
	}

	public static synchronized OptimizerFactory makeInstance() {
		if (factory == null) {
			factory = new OptimizerFactory();
		}
		return factory;
	}

}
