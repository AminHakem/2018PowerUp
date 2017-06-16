package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SPI;

/**
 * The Constants stores constant values for all subsystems. This includes the
 * port values for motors and sensors, as well as important operational
 * constants for subsystems such as max and min values.
 */

// have two buttons: one for shifting to high gear and the other for shifting to
// low gear

public class Constants {
  public static class OI {
    public final static int XBOX_CONTROLLER_PORT = 0;
    public static final int GAME_PAD_PORT = 2;

    // Xbox Controller Ports
    public final static int SHIFT_LOW_PORT = 9;
    public final static int SHIFT_HIGH_PORT = 10;
    public final static int RUN_INTAKE_PORT = 6;
    public final static int REVERSE_INTAKE_PORT = 8;
    public final static int RUN_INDEXWHEEL_PORT = 5;
    public final static int REVERSE_INDEXWHEEL_PORT = 7;
    public static final int BRAKE_CANTALONS_PORT = 1;
    public static final int COAST_CANTALONS_PORT = 3;
    public static final int CLIMB_PORT = 4;
    public static final int STOP_CLIMB_PORT = 2;

    // Game Pad Ports
    public final static int RUN_FLYWHEEL_PORT = 1;
    public static final int STOP_FLYWHEEL_PORT = 3;
    public static final int REVERSE_FLYWHEEL_PORT = 4;
    public static final int INCREASE_SHOOTER_SPEED_PORT = 8;
    public static final int DECREASE_SHOOTER_SPEED_PORT = 7;

    public static final int RESET_SHOOTER_SPEED_PORT = 2;
    public static final int SHIFT_GEAR_MANIPULATOR_HIGH_PORT = 6;
    public static final int SHIFT_GEAR_MANIPULATOR_LOW_PORT = 5;

    // 5 is piston out (gear manipulator)
    // 6 is piston in (gear manipulator)

  }

  public static class Shooter {
    // MOTOR CONTROLLERS
    public static final int FLY_WHEEL1 = 5;
    public static final int FLY_WHEEL2 = 6;
    public static final int INDEX_WHEEL = 7;

    public final static int HALL_EFFECT_PORT = 9;
  }

  public static class DriveTrain {
    public static final int PISTON_MODULE = 10;
    public static final int GEAR_MANIPULATOR_PISTON_FORWARD = 4,
        GEAR_MANIPULATOR_PISTON_REVERSE = 5;

    public static final int DRIVETRAIN_GEAR_FORWARD = 0,
        DRIVETRAIN_GEAR_REVERSE = 1;

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

  public static class Climber {
    public static final int WINCH_PORT = 0;
  }

  public static enum Direction {
    LEFT, RIGHT, DOWN, UP, FORWARD, BACKWARD;
  }
}
