package org.usfirst.frc.team3501.robot;

/**
 * The Constants stores constant values for all subsystems. This includes the
 * port values for motors and sensors, as well as important operational
 * constants for subsystems such as max and min values.
 */

public class Constants {
  public static class OI {
    public final static int LEFT_STICK_PORT = 0;
    public final static int RIGHT_STICK_PORT = 0;
  }

  public static class DriveTrain {
    public static final int FRONT_LEFT = 0;
    public static final int FRONT_RIGHT = 0;
    public static final int REAR_LEFT = 0;
    public static final int REAR_RIGHT = 0;
  }

  public static enum Direction {
    LEFT, RIGHT, DOWN, UP, FORWARD, BACKWARD;
  }
}
