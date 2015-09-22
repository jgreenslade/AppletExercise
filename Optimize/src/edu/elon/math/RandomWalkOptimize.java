package edu.elon.math;

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
