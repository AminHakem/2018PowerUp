package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.SPI;

/**
 * The Constants stores constant values for all subsystems. This includes the port values for motors
 * and sensors, as well as important operational constants for subsystems such as max and min
 * values.
 */

// have two buttons: one for shifting to high gear and the other for shifting to
// low gear

public class Constants {
  public static class OI {
    // CONTROLLERS
    public final static int PS4_CONTROLLER_PORT = 3;
    public static final int GAME_PAD_PORT = 2;

    // Buttons
    public static final int SQUARE_BUTTON_PORT = 1;
    public static final int X_BUTTON_PORT = 2;
    public static final int CIRCLE_BUTTON_PORT = 3;
    public static final int TRIANGLE_BUTTON_PORT = 4;

    // TODO: Confirm with Collin button mapping, then fill in the 1's below (filler number) with
    // button constants above

    // Drive Train
    public static final int FIELD_ORIENTED_PORT = 1;
    public static final int VISION_ALIGN_PORT = 1;

    // Intake
    public static final int RUN_INTAKE_PORT = SQUARE_BUTTON_PORT;
    public static int REVERSE_INTAKE_PORT = X_BUTTON_PORT;

    // Climber
    public static final int TOGGLE_PISTON_PORT = 0;
    public static final int LOWER_RAMP = 1;
    public static final int RUN_WINCH_FORWARD = 1;
    public static final int RUN_WINCH_BACKWARD = 1;
    public static final int TOGGLE_CLIMB = 1;

    // Elevator
    public static final int ELEVATOR_TO_SWITCH = CIRCLE_BUTTON_PORT;
    public static final int ELEVATOR_TO_SCALE_LOW = TRIANGLE_BUTTON_PORT;
    public static final int ELEVATOR_TO_SCALE_NEUTRAL = 1;
    public static final int ELEVATOR_TO_BOTTOM = 1;
    public static final double ACCELERATION_CONTROL = 0.3;
  }

  public static class Elevator {
    // LIFT MOTOR CONTROLLERS
    public static final int ELEVATOR_MOTOR = 9;

    // HALL EFFECT LIMIT SENSORS
    public static final int TOP_LIMIT_SWITCH = 0;
    public static final int BOTTOM_LIMIT_SWITCH = 1;

    public static final int PISTON_HOOK = 0;

    // LIFT ENCODERS
    public static final int ELEVATOR_ENCODER_A = 9;
    public static final int ELEVATOR_ENCODER_B = 8;

    // POSITIONS (in inches)
    public static final int START_POS = 6;
    public static final int BOTTOM_POS = 0;
    public static final int TOP_POS = 71; // assumes at max height for robot
    public static final int SWITCH_POS = 19;
    public static final int SCALE_START_POS = 60; // assumes scale is at its
                                                  // starting position
    public static final int SCALE_BOTTOM_POS = 48; // assumes scale is at bottom
                                                   // position
    // there is no scale_top_pos because exceeds robot max height

    // PID VALUES
    public static double CLIMB_P = 0.01, CLIMB_I = 0.00115, CLIMB_D = -0.002;
  }

  public static class DriveTrain {
    // MOTOR CONTROLLERS
    public static final int FRONT_LEFT = 5;
    public static final int FRONT_RIGHT = 7;
    public static final int REAR_LEFT = 4;
    public static final int REAR_RIGHT = 1;
    public static final SPI.Port GYRO_PORT = SPI.Port.kOnboardCS0;
  }

  public static class Intake {
    // MOTOR CONTROLLERS
    public static final int INTAKE_PORT = 8;
  }

  public static class Climber {
    public static final int PISTON_MODULE = 0;
    public static final int PISTON_CHANNEL = 0;
    public static final int RIGHT_WINCH = 2;
    public static final int LEFT_WINCH = 3;
  }

  public static class Auton {
    public static final double SCALE_FACTOR_TESTING = 0.1;
    public static final boolean AUTON_DEBUGGING = false;
    public static final boolean INCLUDE_TIME_STAMP = false;
  }

  public static enum Direction {
    LEFT, RIGHT, DOWN, UP, FORWARD, BACKWARD;
  }

}
