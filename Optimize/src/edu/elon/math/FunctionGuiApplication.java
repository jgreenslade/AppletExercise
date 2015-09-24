/**
 * Copyright (c) 2015, Jacopo Greenslade
 */
package edu.elon.math;

import javax.swing.JFrame;

/**
 * Starts the GUI function application
 * 
 * @author jgreenslade
 *
 */
public class FunctionGuiApplication {

  public static void main(String[] args) {

    Function samsClub = new SamsClub();
    // samsClub.setOptimizeBehavior(new RandomWalkOptimize());
    JFrame gui1 = new DinamicGui(samsClub);

    Function dell = new Dell();
    // dell.setOptimizeBehavior(new RandomWalkOptimize());
    JFrame gui2 = new DinamicGui(dell);

    Function mAS = new MinimumAbsoluteSum();
    // mAS.setOptimizeBehavior(new PowellOptimize());
    JFrame gui3 = new DinamicGui(mAS);
  }
}