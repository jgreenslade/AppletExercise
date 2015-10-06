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
    JFrame gui1 = new DinamicGui(samsClub);
    gui1.setLocation(300, 400);

    Function dell = new Dell();
    JFrame gui2 = new DinamicGui(dell);
    gui2.setLocation(550, 400);

    Function mAS = new MinimumAbsoluteSum();
    JFrame gui3 = new DinamicGui(mAS);
    gui3.setLocation(800, 400);
  }
}