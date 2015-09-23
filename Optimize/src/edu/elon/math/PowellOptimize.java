package edu.elon.math;

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
