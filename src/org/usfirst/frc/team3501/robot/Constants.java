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

    // Need to fill in the port numbers of the following buttons
    public final static int TOGGLE_WINCH_PORT = 0;
    public final static int TOGGLE_FLYWHEEL_PORT = 0;
    public final static int TOGGLE_INDEXWHEEL_PORT = 0;
    public final static int REVERSE_INDEXWHEEL_PORT = 0;
    public final static int TOGGLE_GEAR_PORT = 5;
    public final static int TOGGLE_INTAKE_PORT = 1;
    public final static int REVERSE_INTAKE_PORT = 2;
  }

  public static class Shooter {
    // MOTOR CONTROLLERS
    public static final int FLY_WHEEL1 = 0;
    public static final int FLY_WHEEL2 = 0;
    public static final int INDEX_WHEEL = 0;
    public final static int TOGGLE_WINCH_PORT = 0;

    public final static int TOGGLE_FLYWHEEL_PORT = 0;
    public final static int TOGGLE_INDEXWHEEL_PORT = 0;

    public final static int HALL_EFFECT_PORT = 4;
  }

  public static class DriveTrain {
    // GEARS
    public static final int LEFT_GEAR_PISTON_FORWARD = 0,
        LEFT_GEAR_PISTON_REVERSE = 1, RIGHT_GEAR_PISTON_FORWARD = 2,
        RIGHT_GEAR_PISTON_REVERSE = 3;
    public static final Value HIGH_GEAR = DoubleSolenoid.Value.kForward;
    public static final Value LOW_GEAR = DoubleSolenoid.Value.kReverse;

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
