package org.usfirst.frc.team3501.robot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MathLib {

  /***
   * This method gives speed as a function of % distance covered so the speed
   * forms a parabola starting and ending at minSpeed when you start and end and
   * achieving maxSpeed exactly halfway.
   *
   * @param minSpeed
   *          the starting and ending speed, in range [0, 1]
   * @param maxSpeed
   *          the max speed, achieved at percentComplete = 1/2.
   * @param percentComplete
   *          should be currentDistance / targetDistance
   * @return the speed (motor value) to set motors to for smooth acceleration.
   *         Note that since velocity is a parabola, acceleration is linear. It
   *         may exceed the maximum value robot can accelerate without wheel
   *         slipping.
   */
  public static double getSpeedForLinearAccel(double minSpeed, double maxSpeed,
      double percentComplete) {
    return 4 * (minSpeed - maxSpeed) * (percentComplete - 0.5)
        * (percentComplete - 0.5) + maxSpeed;
  }

  /***
   * This method gives speed as a function of % distance covered so the speed
   * increases linearly from minSpeed to maxSpeed and then back down again.
   *
   * @param minSpeed
   *          the starting and ending speed, in range [0, 1]
   * @param maxSpeed
   *          the max speed, achieved at percentComplete = 1/2.
   * @param percentComplete
   *          should be currentDistance / targetDistance
   * @return the speed (motor value) to set motors to.
   */
  public static double getSpeedForConstantAccel(double minSpeed,
      double maxSpeed, double percentComplete) {
    return maxSpeed
        + 2 * (minSpeed - maxSpeed) * Math.abs(percentComplete - 0.5);
  }

  /***
   * Restricts an input value to the range [low, high]. If value > high it will
   * be set to high. If value < low it will be set to low.
   *
   * This method is used for defensive programming for inputs to motors to
   * restrict them to valid ranges.
   *
   * @param value
   *          the value to restrict.
   * @param low
   *          the smallest acceptable value.
   * @param high
   *          the largest acceptable value.
   * @return returns value restricted to be within the range [low, high].
   */
  public static double restrictToRange(double value, double low, double high) {
    value = Math.max(value, low);
    value = Math.min(value, high);
    return value;
  }

  /***
   * Returns true if val is in the range [low, high]
   *
   * @param val
   *          value to test
   * @param low
   *          low end of range
   * @param high
   *          high end of range
   * @return boolean return true if val is in the range [low, high]
   */
  public static boolean inRange(double val, double low, double high) {
    return (val <= high) && (val >= low);
  }

  public static double limitValue(double val) {
    return limitValue(val, 1.0);
  }

  public static double limitValue(double val, double max) {
    if (val > max) {
      return max;
    } else if (val < -max) {
      return -max;
    } else {
      return val;
    }
  }

  public static double limitValue(double val, double min, double max) {
    if (val > max) {
      return max;
    } else if (val < min) {
      return min;
    } else {
      return val;
    }
  }

  public static double calcLeftTankDrive(double x, double y) {
    return limitValue(y + x);
  }

  public static double calcRightTankDrive(double x, double y) {
    return limitValue(y - x);
  }

  public static void debugPrint(String message) {
    if (Constants.Auton.AUTON_DEBUGGING) {
      if (Constants.Auton.INCLUDE_TIME_STAMP) {
        message = getTimeStamp() + ": " + message;
      }
      System.out.println(message);
    }

  }

  public static String getTimeStamp() {
    Date d = new Date();
    SimpleDateFormat dateFormatter = new SimpleDateFormat("h:m:s:S");
    System.out.println("Format 1:   " + dateFormatter.format(d));
    return "Format 1:   " + dateFormatter.format(d);
  }

  // TODO Auto-generated method stub
  // TODO Auto-generated method stub

}
