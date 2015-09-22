package edu.elon.math;

import java.util.Observer;

import javax.swing.JFrame;

public class FunctionGuiApplication {

	public static void main(String[] args) {
		Function samsClub = new SamsClub();
		samsClub.setOptimizeBehavior(new RandomWalkOptimize());
		JFrame gui1 = new DinamicGui(samsClub);
		
		Function dell = new Dell();
		dell.setOptimizeBehavior(new RandomWalkOptimize());
		JFrame gui2 = new DinamicGui(dell);
		
		Function mAS = new MinimumAbsoluteSum();
		mAS.setOptimizeBehavior(new RandomWalkOptimize());
		JFrame gui3 = new DinamicGui(mAS);
	}

}