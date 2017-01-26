package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.commands.driving.JoystickDrive;
import org.usfirst.frc.team3501.robot.utils.BNO055;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
  public static double driveP = 0.006, driveI = 0.001, driveD = -0.002;
  public static double defaultGyroP = 0.004, defaultGyroI = 0.0013,
      defaultGyroD = -0.005;
  public static double driveP = 0.008, driveI = 0.001, driveD = -0.002;
  public static double defaultGyroP = 0.009, defaultGyroI = 0.00000,
      defaultGyroD = -0.000;
  private double gyroZero = 0;

  public static final double WHEEL_DIAMETER = 6; // inches
  public static final int ENCODER_PULSES_PER_REVOLUTION = 256;
  public static final double INCHES_PER_PULSE = WHEEL_DIAMETER * Math.PI
      / ENCODER_PULSES_PER_REVOLUTION;

  private static DriveTrain driveTrain;
  private final CANTalon frontLeft, frontRight, rearLeft, rearRight;
  private final RobotDrive robotDrive;
  private final Encoder leftEncoder, rightEncoder;
  private final DoubleSolenoid leftGearPiston, rightGearPiston;

  private ADXRS450_Gyro imu;
  private BNO055 imu;

  private DriveTrain() {
    // MOTOR CONTROLLERS
    frontLeft = new CANTalon(Constants.DriveTrain.FRONT_LEFT);
    frontRight = new CANTalon(Constants.DriveTrain.FRONT_RIGHT);
    rearLeft = new CANTalon(Constants.DriveTrain.REAR_LEFT);
    rearRight = new CANTalon(Constants.DriveTrain.REAR_RIGHT);

    // ENCODERS
    leftEncoder = new Encoder(Constants.DriveTrain.ENCODER_LEFT_A,
        Constants.DriveTrain.ENCODER_LEFT_B, false, Encoder.EncodingType.k4X);
    rightEncoder = new Encoder(Constants.DriveTrain.ENCODER_RIGHT_A,
        Constants.DriveTrain.ENCODER_RIGHT_B, false, Encoder.EncodingType.k4X);

    leftEncoder.setDistancePerPulse(INCHES_PER_PULSE);
    rightEncoder.setDistancePerPulse(INCHES_PER_PULSE);

    // ROBOT DRIVE
    robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);

    this.imu = new ADXRS450_Gyro(Constants.DriveTrain.GYRO_PORT);
    shifter = DoubleSolenoid(10, Constants.DriveTrain.SHIFTER_FORWARD,
        Constants.DriveTrain.SHIFTER_REVERSE);
    leftGearPiston = new DoubleSolenoid(Constants.DriveTrain.MODULE_NUMBER,
        Constants.DriveTrain.LEFT_FORWARD, Constants.DriveTrain.LEFT_REVERSE);
    rightGearPiston = new DoubleSolenoid(Constants.DriveTrain.MODULE_NUMBER,
        Constants.DriveTrain.RIGHT_FORWARD, Constants.DriveTrain.RIGHT_REVERSE);
  }

  public static DriveTrain getDriveTrain() {
    if (driveTrain == null) {
      driveTrain = new DriveTrain();
    }
    return driveTrain;
  }

  // DRIVE METHODS
  public void setMotorValues(final double left, final double right) {
    frontLeft.set(left);
    rearLeft.set(left);

    frontRight.set(-right);
    rearRight.set(-right);
  }

  public void joystickDrive(final double thrust, final double twist) {
    robotDrive.arcadeDrive(thrust, twist, true);
  }

  public void stop() {
    setMotorValues(0, 0);
  }

  public double getLeftMotorVal() {
    return (frontLeft.get() + rearLeft.get()) / 2;
  }

  public double getRightMotorVal() {
    return (frontRight.get() + rearRight.get()) / 2;
  }

  // ENCODER METHODS

  public double getLeftEncoderDistance() {
    return leftEncoder.getDistance();
  }

  public double getRightEncoderDistance() {
    return rightEncoder.getDistance();
  }

  public void printEncoderOutput() {
    // System.out.println("left: " + getLeftEncoderDistance());
    // System.out.println("right: " + getRightEncoderDistance());
    System.out.println(getAvgEncoderDistance());
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
    return this.imu.getAngle() - this.gyroZero;
  }

  public void resetGyro() {
    this.imu.reset();
  }

  public double getZeroAngle() {
    return this.gyroZero;
  /*
   * @return a value that is the current setpoint for the piston kReverse or
   * KForward
   */
  public Value getLeftGearPistonValue() {
    return leftGearPiston.get();
  }

  /*
   * @return a value that is the current setppoint for the piston kReverse or
   * kForward
   */
  public Value getRightGearPistonValue() {
    return rightGearPiston.get();
  }

  /*
   * Changes the ball shift gear assembly to high
   */
  public void setHighGear() {
    changeGear(Constants.DriveTrain.HIGH_GEAR);
  }

  /*
   * Changes the ball shift gear assembly to low
   */
  public void setLowGear() {
    changeGear(Constants.DriveTrain.LOW_GEAR);
  }

  /*
   * Changes the gear to a DoubleSolenoid.Value
   */
  private void changeGear(DoubleSolenoid.Value gear) {
    leftGearPiston.set(gear);
    rightGearPiston.set(gear);
  }

  public double getAngle() {
    if (!this.imu.isInitialized())
      return -1;
    return this.imu.getHeading() - this.gyroZero;
  }

  public void resetGyro() {
    this.gyroZero = this.getAngle();

  }

  public double getZeroAngle() {
    return this.gyroZero;
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new JoystickDrive());
  }

}
