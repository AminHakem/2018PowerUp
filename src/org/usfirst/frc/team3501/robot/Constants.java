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
    public static final int TRIANGLE_BUTTON_PORT = 4;
    public static final int X_BUTTON_PORT = 2;

    // Intake
    public static final int RUN_INTAKE_PORT = 2;
    public static int REVERSE_INTAKE_PORT = 2;

    // Climber
    public static final int LOWER_RAMP = 0;
    public static final int RUN_WINCH_FORWARD = 0;
    public static final int RUN_WINCH_BACKWARD = 0;
    public static final int TOGGLE_CLIMB = 0;
  }

  public static class Elevator {
    // LIFT MOTOR CONTROLLERS
    public static final int ELEVATOR = 1;

    // LIFT ENCODERS
    public static final int ELEVATOR_ENCODER_A = 0;
    public static final int ELEVATOR_ENCODER_B = 1;

  }

  public static class DriveTrain {
    // MOTOR CONTROLLERS
    public static final int FRONT_LEFT = 5;
    public static final int FRONT_RIGHT = 2;
    public static final int REAR_LEFT = 6;
    public static final int REAR_RIGHT = 3;

    // ENCODERS
    public static final int ENCODER_LEFT_A = 1;
    public static final int ENCODER_LEFT_B = 0;
    public static final int ENCODER_RIGHT_A = 2;
    public static final int ENCODER_RIGHT_B = 3;

    public static final SPI.Port GYRO_PORT = SPI.Port.kOnboardCS0;
  }

  public static class Intake {
    // MOTOR CONTROLLERS
    public static final int INTAKE_LEFT = 0;
    public static final int INTAKE_RIGHT = 0;
  }

  public static class Climber {
    public static final int PISTON_MODULE = 0;
    public static final int PISTON_CHANNEL = 0;
    public static final int RIGHT_WINCH = 0;
    public static final int LEFT_WINCH = 0;
  }

  public static enum Direction {
    LEFT, RIGHT, DOWN, UP, FORWARD, BACKWARD;
  }

}
