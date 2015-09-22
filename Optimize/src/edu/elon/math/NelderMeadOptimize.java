package edu.elon.math;

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
