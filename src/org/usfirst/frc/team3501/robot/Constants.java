package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SPI;

/**
 * The Constants stores constant values for all subsystems. This includes the
 * port values for motors and sensors, as well as important operational
 * constants for subsystems such as max and min values.
 */

public class Constants {
  public static class OI {
    public final static int LEFT_STICK_PORT = 0;
    public final static int RIGHT_STICK_PORT = 1;
    public static final int GAME_PAD_PORT = 2;

    public final static int TOGGLE_GEAR_PORT = 5;
    public final static int RUN_INTAKE_PORT = 1;
    public final static int REVERSE_INTAKE_PORT = 4;

    public final static int RUN_INDEXWHEEL_PORT = 1;
    public final static int REVERSE_INDEXWHEEL_PORT = 2;
    public static final int BRAKE_CANTALONS_PORT = 5;
    public static final int COAST_CANTALONS_PORT = 3;

    public final static int TOGGLE_FLYWHEEL_PORT = 4;
    public static final int REVERSE_FLYWHEEL_PORT = 1;
    public static final int INCREASE_SHOOTER_SPEED_PORT = 6;
    public static final int DECREASE_SHOOTER_SPEED_PORT = 2;
    public static final int TOGGLE_GEAR_MANIPULATOR_PORT = 3;
  }

  public static class Shooter {
    // MOTOR CONTROLLERS
    public static final int FLY_WHEEL1 = 5;
    public static final int FLY_WHEEL2 = 6;
    public static final int INDEX_WHEEL = 7;

    public final static int HALL_EFFECT_PORT = 9;

    public static final int MODULE_NUMBER = 10, PISTON_FORWARD = 4,
        PISTON_REVERSE = 5;
    public static final Value HIGH_GEAR = DoubleSolenoid.Value.kForward;
    public static final Value LOW_GEAR = DoubleSolenoid.Value.kReverse;
  }

  public static class DriveTrain {
    public static final int PISTON_MODULE = 10;
    public static final int GEAR_MANIPULATOR_PISTON_FORWARD = 4,
        GEAR_MANIPULATOR_PISTON_REVERSE = 5;
    public static final int LEFT_GEAR_PISTON_FORWARD = 0,
        LEFT_GEAR_PISTON_REVERSE = 1, RIGHT_GEAR_PISTON_FORWARD = 3,
        RIGHT_GEAR_PISTON_REVERSE = 2;
    public static final Value FORWARD_PISTON_VALUE = DoubleSolenoid.Value.kForward;
    public static final Value REVERSE_PISTON_VALUE = DoubleSolenoid.Value.kReverse;

    // MOTOR CONTROLLERS
    public static final int FRONT_LEFT = 1;
    public static final int FRONT_RIGHT = 3;
    public static final int REAR_LEFT = 2;
    public static final int REAR_RIGHT = 4;

    // ENCODERS
    public static final int ENCODER_LEFT_A = 1;
    public static final int ENCODER_LEFT_B = 0;
    public static final int ENCODER_RIGHT_A = 2;
    public static final int ENCODER_RIGHT_B = 3;

    public static final SPI.Port GYRO_PORT = SPI.Port.kOnboardCS0;
  }

  public static class Intake {
    public static final int INTAKE_ROLLER_PORT = 8;

  }

  public static enum Direction {
    LEFT, RIGHT, DOWN, UP, FORWARD, BACKWARD;
  }
}
