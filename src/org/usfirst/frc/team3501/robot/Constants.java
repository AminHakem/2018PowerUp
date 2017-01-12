package org.usfirst.frc.team3501.robot;

/**
 * The Constants stores constant values for all subsystems. This includes the
 * port values for motors and sensors, as well as important operational
 * constants for subsystems such as max and min values.
 */

public class Constants {
  public static class OI {
    public final static int LEFT_STICK_PORT = 0;
    public final static int RIGHT_STICK_PORT = 1;
  }

  public static class DriveTrain {
    // MOTOR CONTROLLERS
    public static final int FRONT_LEFT = 1;
    public static final int FRONT_RIGHT = 3;
    public static final int REAR_LEFT = 2;
    public static final int REAR_RIGHT = 4;

    // ENCODERS
    public static final int ENCODER_LEFT_A = 0;
    public static final int ENCODER_LEFT_B = 1;
    public static final int ENCODER_RIGHT_A = 2;
    public static final int ENCODER_RIGHT_B = 3;

    public static final int INCHES_PER_PULSE = 0;
  }

  public static enum Direction {
    LEFT, RIGHT, DOWN, UP, FORWARD, BACKWARD;
  }
}
