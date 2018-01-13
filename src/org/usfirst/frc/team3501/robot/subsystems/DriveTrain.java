package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.MathLib;
import org.usfirst.frc.team3501.robot.commands.driving.JoystickDrive;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {
  public static double driveP = 0.01, driveI = 0.00115, driveD = -0.002;
  public static double smallTurnP = 0.004, smallTurnI = 0.0013,
      smallTurnD = 0.005;
  public static double largeTurnP = .003, largeTurnI = .0012, largeTurnD = .006;
  public static double driveStraightGyroP = 0.01;

  public static final double WHEEL_DIAMETER = 4; // inches
  public static final double ENCODER_PULSES_PER_REVOLUTION = 256;
  public static final double INCHES_PER_PULSE =
      WHEEL_DIAMETER * Math.PI / ENCODER_PULSES_PER_REVOLUTION;

  private static DriveTrain driveTrain;

  private final DifferentialDrive robotDrive;

  private final WPI_TalonSRX frontLeft, frontRight, rearLeft, rearRight;
  private final Encoder leftEncoder, rightEncoder;

  private ADXRS450_Gyro imu;

  private DriveTrain() {
    // MOTOR CONTROLLERS
    frontLeft = new WPI_TalonSRX(Constants.DriveTrain.FRONT_LEFT);
    frontRight = new WPI_TalonSRX(Constants.DriveTrain.FRONT_RIGHT);
    rearLeft = new WPI_TalonSRX(Constants.DriveTrain.REAR_LEFT);
    rearRight = new WPI_TalonSRX(Constants.DriveTrain.REAR_RIGHT);

    // ENCODERS
    leftEncoder = new Encoder(Constants.DriveTrain.ENCODER_LEFT_A,
        Constants.DriveTrain.ENCODER_LEFT_B, false, Encoder.EncodingType.k4X);
    rightEncoder = new Encoder(Constants.DriveTrain.ENCODER_RIGHT_A,
        Constants.DriveTrain.ENCODER_RIGHT_B, false, Encoder.EncodingType.k4X);

    leftEncoder.setDistancePerPulse(INCHES_PER_PULSE);
    rightEncoder.setDistancePerPulse(INCHES_PER_PULSE);

    SpeedControllerGroup m_left = new SpeedControllerGroup(frontLeft, rearLeft);

    SpeedControllerGroup m_right =
        new SpeedControllerGroup(frontRight, rearRight);

    robotDrive = new DifferentialDrive(m_left, m_right);

    this.imu = new ADXRS450_Gyro(Constants.DriveTrain.GYRO_PORT);
  }

  public static DriveTrain getDriveTrain() {
    if (driveTrain == null) {
      driveTrain = new DriveTrain();
    }
    return driveTrain;
  }

  // DRIVE METHODS
  public void setMotorValues(double left, double right) {
    left = MathLib.restrictToRange(left, -1.0, 1.0);
    right = MathLib.restrictToRange(right, -1.0, 1.0);

    frontLeft.set(ControlMode.PercentOutput, left);
    rearLeft.set(ControlMode.PercentOutput, left);

    frontRight.set(ControlMode.PercentOutput, -right);
    rearRight.set(ControlMode.PercentOutput, -right);
  }

  public void stop() {
    setMotorValues(0, 0);
  }

  public double getLeftMotorVal() {
    return (frontLeft.getMotorOutputPercent()
        + rearLeft.getMotorOutputPercent()) / 2;
  }

  public double getRightMotorVal() {
    return (frontRight.getMotorOutputPercent()
        + rearRight.getMotorOutputPercent()) / 2;
  }

  // ENCODER METHODS
  public double getLeftEncoderDistance() {
    return leftEncoder.getDistance();
  }

  public double getRightEncoderDistance() {
    return rightEncoder.getDistance();
  }

  public void printEncoderOutput() {
    System.out.println("left: " + getLeftEncoderDistance());
    System.out.println("right: " + getRightEncoderDistance());
  }

  public double getAvgEncoderDistance() {
    return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
  }

  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public double getLeftSpeed() {
    return leftEncoder.getRate();
  }

  public double getRightSpeed() {
    return rightEncoder.getRate();
  }

  public double getSpeed() {
    return (getLeftSpeed() + getRightSpeed()) / 2.0;
  }

  // ------Gyro------//
  public double getAngle() {
    return this.imu.getAngle();
  }

  public void resetGyro() {
    this.imu.reset();
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new JoystickDrive());
  }
}
