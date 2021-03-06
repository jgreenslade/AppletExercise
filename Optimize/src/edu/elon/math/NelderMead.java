/*
 * NelderMead.java 1.0 August 20, 2014
 *
 * Copyright (c) 2014 David J. Powell, Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import java.util.ArrayList;
import java.util.Vector;

import flanagan.math.Minimization;
import flanagan.math.MinimizationFunction;

/**
 * Nelder Mead also known as direct simplex method is a widely used
 * nonlinear unconstrained optimization technique. The goSimplex code
 * is only partially implemented and always returns a optimal value of
 * 0.0. This class will be replaced in a follow on homework assignment
 * using the Adapter design pattern and code from Michael Flanagan
 * 
 * @author dpowell2
 * @version 1.0
 */
public class NelderMead implements OptimizeBehavior {

  private final double initialSimplexSize = .1;
  private int nDim;
  private double[] startPoint;
  private double[][] vertices;
  
  private Minimization flanaganMinimization;
  private MinimizationFunction minFunction;

  /**
   * Default constructor to satisfy coding best practices
   */
  public NelderMead() {
    // intentionally empty
	  flanaganMinimization = new Minimization();
  }

  /**
   * Arrays will start at 1 instead of 0 so we need to allocate 1 more
   * space than anticipated.
   * 
   * @param nDim the number of elements in the input
   */
  public void allocateArrays(int nDim) {
    startPoint = new double[nDim + 1];
    vertices = new double[nDim + 2][nDim + 1];
  }

  /**
   * 
   * Leaves optimal design as the value of the parameter, function
   * instance, field called inputValues;
   * 
   * @param function Function instance containing function starting
   *          point and evaluation logic
   * @return Double value for optimal design.
   */
  public Double goSimplex(Function function) {
    double objective = 0.0;
    nDim = function.getInputValues().size();
    allocateArrays(nDim);
    setStartingPoint(function.getInputValues());
    // similarity check
    getElonCopyrightVertices();
    createInitialSetOfPoints();
    
    // This is my code :) ---------------------------------------------------------------- Jacooooooooooooooooooo -----------------------
    System.out.println("Expected: " + function.getInputValues().size() +"\nActual: " + startPoint.length);
	  minFunction = new MinimizationFunction() {
	
		  
		@Override
		public double function(double[] param) {
			double result = function.evaluate();
			if (function.isMinimize()) {
				return result;
			}
			return result * (-1);
		}
	};
	double[] step = new double[startPoint.length];
	for (int i = 0; i < startPoint.length; i++) {
		if (Math.abs(startPoint[i]) < 1) {
			step[i] = 1.0;
		} else {
			step[i] = 0.5*startPoint[i];
		}
	}
	 flanaganMinimization.nelderMead(minFunction, startPoint, step, 1e-15);
	 
	 function.setInputValues(convertDoubleArrayToArrayList(startPoint));
    
    System.out.println("Nelder Mead stub invoked");
    //return new Double(objective);
    return function.getOutput();
 // This is the end of my code :) --------------------------------------------------- Jacooooooooooooooooooooo  ----------------------
  }

  /**
   * Converts a input point repesented as a one dimensional array of
   * doubles into an arraylist of double
   * 
   * @param aInputArray input array of double values
   * @return input arraylist of Double values
   */
  private ArrayList<Double> convertDoubleArrayToArrayList(
          double[] aInputArray) {
    ArrayList<Double> bestInputPoint = new ArrayList<Double>();
    for (double d : aInputArray) {
      bestInputPoint.add(d);
    }
    return bestInputPoint;
  }
  
  @Override
  public Double optimize(Function function) {
    Double optimalValue = this.goSimplex(function);
    return optimalValue;
  }

  @SuppressWarnings("unused")
  private void amoeba() {
    Vector<Double> pSum = new Vector<Double>();
    pSum.add(10.0); // first element is a dummy placeholder
  }

  @SuppressWarnings("unused")
  private void amoeba(double[][] p, double[] y, int elondim, float ftol,
          Integer nFunk) {
    // not implemented
  }

  private void createInitialSetOfPoints() {
	  
    for (int i = 2; i < nDim + 2; i++) {
      for (int j = 1; j < startPoint.length; j++) {
        double value = initialSimplexSize * startPoint[j];
        if (Math.abs(startPoint[j]) <= .5) {
          value = 1.0;
        }
        if ((i - 1) == j) {
          vertices[i][j] = value;
        } else {
          vertices[i][j] = startPoint[j];
        }
      }
    }
  }

  private double[][] getElonCopyrightVertices() {
    return vertices;
  }

  @SuppressWarnings("unused")
  private int getNDim() {
    return nDim;
  }

  @SuppressWarnings("unused")
  private double[] getStartPoint() {
    return startPoint;
  }

  private void setStartingPoint(ArrayList<Double> values) {
    // 0 row index is the dummy position
    int col;
    int row;
    for (col = 0; col < vertices[0].length; col++) {
      vertices[0][col] = 0;
    }
    // 0 column is not used on each row
    for (row = 0; row < vertices.length; row++) {
      vertices[row][0] = 0.0;
    }
    // row 1 is the entry design point
    row = 1;
    for (col = 0; col < values.size(); col++) {
      startPoint[col + 1] = values.get(col).doubleValue();
      vertices[row][col + 1] = startPoint[col + 1];
    }
  }

}
