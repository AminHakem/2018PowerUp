package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.SPI;

/**
 * <<<<<<< HEAD The Constants stores constant values for all subsystems. This includes the port
 * values for motors and sensors, as well as important operational constants for subsystems such as
 * max and min values. ======= change servo to two pistons The Constants stores constant values for
 * all subsystems. This includes the port values for motors and sensors, as well as important
 * operational constants for subsystems such as max and min values. >>>>>>> change servo to two
 * pistons
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
    public static final int L1_BUTTON_PORT = 5;
    public static final int R1_BUTTON_PORT = 6;
    public static final int L2_BUTTON_PORT = 7;
    public static final int R2_BUTTON_PORT = 8;
    public static final int PS_BUTTON_PORT = 13;
    public static final int PS_SHARE_BUTTON = 9;

    // TODO: Confirm with Collin button mapping, then fill in the 0's below
    // (filler number) with
    // button constants above

    // Drive Train
    public static final int FIELD_ORIENTED_PORT = TRIANGLE_BUTTON_PORT;
    public static final int VISION_ALIGN_PORT = X_BUTTON_PORT;

    // Intake
    public static int RUN_INTAKE_PORT = SQUARE_BUTTON_PORT;
    public static int REVERSE_INTAKE_PORT = CIRCLE_BUTTON_PORT;
    public static int SHOOT_PORT = PS_SHARE_BUTTON;
    public static int INTAKE_PISTON_PORT = L2_BUTTON_PORT;
    // Climber
    public static final int TOGGLE_CLIMBER_PISTON_PORT = PS_BUTTON_PORT;
    public static final int LOWER_RAMP = 0;
    public static final int RUN_WINCH_FORWARD = 0;
    public static final int RUN_WINCH_BACKWARD = 14;
    public static final int TOGGLE_CLIMB = 0;

    // Elevator
    public static final int ELEVATOR_TO_SWITCH = R1_BUTTON_PORT;
    public static final int ELEVATOR_TO_SCALE = R2_BUTTON_PORT;
    public static final int ELEVATOR_TO_VAULT = 10;
    public static final int ELEVATOR_TO_BOTTOM = L1_BUTTON_PORT;
  }

  public static class Elevator {
    // LIFT MOTOR CONTROLLERS
    public static final int ELEVATOR_MOTOR = 9;
    public static final int ELEVATOR_ENCODER_TALON = 2;

    // HALL EFFECT LIMIT SENSORS
    public static final int TOP_LIMIT_SWITCH = 1;
    public static final int BOTTOM_LIMIT_SWITCH = 0;

    public static final int PISTON_CHANNEL = 1;

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
    public static final int INTAKE_PISTON_PORT = 6;

  }

  public static class Climber {
    public static final int PISTON_CHANNEL = 7;
    public static final int RIGHT_WINCH = 2;
    public static final int LEFT_WINCH = 3;
  }

  public static class Auton {
    public static final double SCALE_FACTOR_TESTING = 1;
    public static final boolean AUTON_DEBUGGING = false;
    public static final boolean INCLUDE_TIME_STAMP = false;
  }

  public static enum Direction {
    LEFT, RIGHT, DOWN, UP, FORWARD, BACKWARD;
  }
  public static class NetworkThread {
    public static final int CAMERA_DATA_INPUT_UDP_PORT = 5800;
  }

}
