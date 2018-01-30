package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.commands.driving.JoystickDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class DriveTrain extends Subsystem {
  // Set PID values, need to test mecanum wheels to find them
  public static double driveP = 0.01, driveI = 0.00115, driveD = -0.002;
  public static double smallTurnP = 0.004, smallTurnI = 0.0013, smallTurnD = 0.005;
  public static double largeTurnP = .003, largeTurnI = .0012, largeTurnD = .006;
  public static double driveStraightGyroP = 0.01;

  // Need to edit for correct wheel diameter
  public static final double WHEEL_DIAMETER = 4; // inches
  public static final double ENCODER_PULSES_PER_REVOLUTION = 256;
  public static final double INCHES_PER_PULSE =
      WHEEL_DIAMETER * Math.PI / ENCODER_PULSES_PER_REVOLUTION;
  private static DriveTrain driveTrain;
  private final MecanumDrive robotDrive;

  private final WPI_TalonSRX frontLeft, frontRight, rearLeft, rearRight;
  private final Encoder frontBackEncoder, rightLeftEncoder;
  public JoystickDrive joystickDrive;
  private boolean fieldOriented;
  private static double threadOutput;

  private ADXRS450_Gyro imu;

  private DriveTrain() {
    // MOTOR CONTROLLERS
    frontLeft = new WPI_TalonSRX(Constants.DriveTrain.FRONT_LEFT);
    frontRight = new WPI_TalonSRX(Constants.DriveTrain.FRONT_RIGHT);
    rearLeft = new WPI_TalonSRX(Constants.DriveTrain.REAR_LEFT);
    rearRight = new WPI_TalonSRX(Constants.DriveTrain.REAR_RIGHT);

    // ENCODERS
    rightLeftEncoder = new Encoder(Constants.DriveTrain.ENCODER_LEFT_A,
        Constants.DriveTrain.ENCODER_LEFT_B, false, Encoder.EncodingType.k4X);
    frontBackEncoder = new Encoder(Constants.DriveTrain.ENCODER_RIGHT_A,
        Constants.DriveTrain.ENCODER_RIGHT_B, false, Encoder.EncodingType.k4X);

    rightLeftEncoder.setDistancePerPulse(INCHES_PER_PULSE);
    frontBackEncoder.setDistancePerPulse(INCHES_PER_PULSE);

    SpeedControllerGroup m_left_rear = new SpeedControllerGroup(rearLeft);
    SpeedControllerGroup m_left_front = new SpeedControllerGroup(frontLeft);
    SpeedControllerGroup m_right_rear = new SpeedControllerGroup(rearRight);
    SpeedControllerGroup m_right_front = new SpeedControllerGroup(frontRight);

    robotDrive = new MecanumDrive(m_left_front, m_left_rear, m_right_front, m_right_rear);

    try {
      System.out.println("Ran the Gyro code");
      this.imu = new ADXRS450_Gyro();
      this.imu.reset();
      this.imu.calibrate();
      // this.imu = new ADXRS450_Gyro(Constants.DriveTrain.GYRO_PORT);
    } catch (NullPointerException e) {
      System.out.println("Gyro Null Pointer Exception");
      this.imu = null;
    }
    fieldOriented = true;

  }

  /**
   *
   * @return driveTrain if one does not already exist
   */
  public static DriveTrain getDriveTrain() {
    if (driveTrain == null) {
      driveTrain = new DriveTrain();
    }
    return driveTrain;
  }

  /**
   * Stop the robot (set motor values to 0)
   */
  public void stop() {
    robotDrive.driveCartesian(0, 0, 0, 0);
  }


  // ENCODER METHODS
  /**
   * Receives the value for the encoder that controls going left and right
   *
   * @return rightLeftEncoder distance
   */

  public double getRightLeftEncoderDistance() {
    return rightLeftEncoder.getDistance();
  }

  /**
   * Receives the value for the encoder that controls going front and back
   *
   * @return frontBackEncoder distance
   */

  public double getFrontBackEncoderDistance() {
    return frontBackEncoder.getDistance();
  }

  /**
   * Prints out both distances of the encoders
   */
  public void printEncoderOutput() {
    System.out.println("left/right: " + getRightLeftEncoderDistance());
    System.out.println("front/back: " + getFrontBackEncoderDistance());
  }

  /**
   * Reset encoder distances to 0
   */
  public void resetEncoders() {
    rightLeftEncoder.reset();
    frontBackEncoder.reset();
  }

  /**
   *
   * @return rightLeft wheel speed
   */
  public double getRightLeftSpeed() {
    return rightLeftEncoder.getRate();
  }

  /**
   *
   * @return frontBack wheel speed
   */
  public double getFrontBackSpeed() {
    return frontBackEncoder.getRate();
  }

  // ------Gyro------//
  /**
   *
   * @return angle
   */
  public double getAngle() {
    if (imu != null)
      return this.imu.getAngle();
    else
      return 0;
  }


  /***
   * Changes the current angle
   *
   * @param angle - Positive input increases the current angle and negative input decreases the
   *        angle
   */
  public void changeAngle(double angle) {
    double ySpeed = Math.cos(angle);
    double xSpeed = Math.sin(angle);
    this.mecanumDrive(ySpeed, xSpeed, 0);
  }

  /**
   * resets accumulator
   */
  public void resetGyro() {
    this.imu.reset();
  }

  /**
   * Mecanum Drive - takes in the ySpeed, xSpeed, Z Rotation, and Gyro Angle as parameters and
   * enters those arguments in the driveCartesian method
   *
   * @param ySpeed
   * @param xSpeed
   * @param rotation
   * @param if statement
   */
  public void mecanumDrive(final double ySpeed, final double xSpeed, final double rotation) {
    if ((ySpeed < 0.1 && ySpeed > -0.1) && (xSpeed < 0.1 && xSpeed > -0.1)
        && (rotation < 0.1 && rotation > -0.1)) {
      robotDrive.stopMotor();
    }
    if (this.fieldOriented) {
      robotDrive.driveCartesian(ySpeed, xSpeed, rotation, -this.getAngle());
    } else {
      robotDrive.driveCartesian(ySpeed, xSpeed, rotation);
    }
  }
  
  public void toggleFieldOriented() {
    this.fieldOriented = !fieldOriented;
  }


  @Override
  protected void initDefaultCommand() {
    this.joystickDrive = new JoystickDrive();
   //setDefaultCommand(joystickDrive);
  }


  public JoystickDrive getJoystickDrive() {
    return joystickDrive;
  }

  public double getFrontLeftMotorPower() {
    return this.frontLeft.getMotorOutputPercent();
  }

  public double getFrontRightMotorPower() {
    return this.frontRight.getMotorOutputPercent();
  }

  public double getRearLeftMotorPower() {
    return this.rearLeft.getMotorOutputPercent();
  }

  public double getRearRightMotorPower() {
    return this.rearRight.getMotorOutputPercent();
  }
  
  public static double getThreadOutput() {
		return threadOutput;
	}

	public static void setThreadOutput(double threadOutput) {
		DriveTrain.threadOutput = threadOutput;
	}
}
