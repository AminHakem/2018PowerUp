package org.usfirst.frc.team3501.robot.utils;

import edu.wpi.first.wpilibj.Timer;

public class TimerUtil {
  private static Timer timer;
  public TimerUtil() {
    
  }
 
  public static void printTime(String printout) {
    System.out.println(printout+timer.get());
  }
  public static double getTime() {
    return timer.get();
  }
  public static void startTime() {
    timer = new Timer();
    timer.start();
  }
}
