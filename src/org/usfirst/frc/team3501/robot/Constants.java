package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.SPI;

/**
 * The Constants stores constant values for all subsystems. This includes the
 * port values for motors
 * and sensors, as well as important operational constants for subsystems such
 * as max and min
 * values.
 */

// have two buttons: one for shifting to high gear and the other for shifting to
// low gear

public class Constants {
  public static class OI {
    public final static int XBOX_CONTROLLER_PORT = 0;
    public static final int GAME_PAD_PORT = 2;

    public static final int RUN_INTAKE_PORT = 0;
    public static int REVERSE_INTAKE_PORT = 0;

  }

  public static class Lift {
    //LIFT MOTOR CONTROLLERS
    public static final int LIFT_LEFT = 1;
    public static final int LIFT_RIGHT = 2;
    
    //LIFT ENCODERS
    public static final int ELEVATOR_ENCODER_LEFT_A = 0;
    public static final int ELEVATOR_ENCODER_LEFT_B = 1;
    public static final int ELEVATOR_ENCODER_RIGHT_A = 2;
    public static final int ELEVATOR_ENCODER_RIGHT_B = 1;
    
  }
  
  public static class DriveTrain {
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
    // MOTOR CONTROLLERS
    public static final int INTAKE_LEFT = 0;
    public static final int INTAKE_RIGHT = 0;

  }

  public static enum Direction {
    LEFT, RIGHT, DOWN, UP, FORWARD, BACKWARD;
  }
}
