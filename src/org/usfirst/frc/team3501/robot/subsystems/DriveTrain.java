package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.MathLib;
import org.usfirst.frc.team3501.robot.commands.driving.JoystickDrive;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
  public static double driveP = 0.012, driveI = 0.0011, driveD = -0.002;
  public static double smallTurnP = 0.004, smallTurnI = 0.0013,
      smallTurnD = 0.005;
  public static double largeTurnP = .003, largeTurnI = .0012, largeTurnD = .006;
  public static double driveStraightGyroP = 0.01;

  public static final double WHEEL_DIAMETER = 4; // inches
  public static final double ENCODER_PULSES_PER_REVOLUTION = 256;
  public static final double INCHES_PER_PULSE = WHEEL_DIAMETER * Math.PI
      / ENCODER_PULSES_PER_REVOLUTION;

  public static final double MAINTAIN_CLIMBED_POSITION = 0;
  public static final double TIME_TO_CLIMB_FOR = 0;
  public static final double CLIMBER_SPEED = 0;

  public static final boolean DRIVE_BRAKE_MODE = true;
  public static final boolean DRIVE_COAST_MODE = false;

  private static DriveTrain driveTrain;

  private final CANTalon frontLeft, frontRight, rearLeft, rearRight;
  private final RobotDrive robotDrive;
  private final Encoder leftEncoder, rightEncoder;
  private final DoubleSolenoid leftDriveTrainPiston, rightDriveTrainPiston;
  private final DoubleSolenoid gearManipulatorPiston;

  private ADXRS450_Gyro imu;

  public boolean shouldBeClimbing = false;

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

    // TODO: Not sure if MODULE_NUMBER should be the same for both
    leftDriveTrainPiston = new DoubleSolenoid(
        Constants.DriveTrain.PISTON_MODULE,
        Constants.DriveTrain.LEFT_GEAR_PISTON_FORWARD,
        Constants.DriveTrain.LEFT_GEAR_PISTON_REVERSE);
    rightDriveTrainPiston = new DoubleSolenoid(
        Constants.DriveTrain.PISTON_MODULE,
        Constants.DriveTrain.RIGHT_GEAR_PISTON_FORWARD,
        Constants.DriveTrain.RIGHT_GEAR_PISTON_REVERSE);

    gearManipulatorPiston = new DoubleSolenoid(
        Constants.DriveTrain.PISTON_MODULE,
        Constants.DriveTrain.GEAR_MANIPULATOR_PISTON_FORWARD,
        Constants.DriveTrain.GEAR_MANIPULATOR_PISTON_REVERSE);
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
    System.out.println("left: " + getLeftEncoderDistance());
    System.out.println("right: " + getRightEncoderDistance());
    // System.out.println(getAvgEncoderDistance());
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

  /*
   * @return a value that is the current setpoint for the piston kReverse or
   * KForward
   */
  public Value getLeftDriveTrainPiston() {
    return leftDriveTrainPiston.get();
  }

  /*
   * @return a value that is the current setpoint for the piston kReverse or
   * KForward
   */
  public Value getRightDriveTrainPiston() {
    return rightDriveTrainPiston.get();
  }

  /*
   * Changes the ball shift gear assembly to high
   */
  public void setHighGear() {
    changeGear(Constants.DriveTrain.FORWARD_PISTON_VALUE);
  }

  /*
   * Changes the ball shift gear assembly to low
   */
  public void setLowGear() {
    changeGear(Constants.DriveTrain.REVERSE_PISTON_VALUE);
  }

  /*
   * Changes the gear to a DoubleSolenoid.Value
   */
  private void changeGear(DoubleSolenoid.Value gear) {
    leftDriveTrainPiston.set(gear);
    rightDriveTrainPiston.set(gear);
  }

  public Value getGearManipulatorPistonValue() {
    return gearManipulatorPiston.get();
  }

  public void extendGearManipulatorPiston() {
    gearManipulatorPiston.set(Constants.DriveTrain.FORWARD_PISTON_VALUE);
  }

  public void retractGearManipulatorPiston() {
    gearManipulatorPiston.set(Constants.DriveTrain.REVERSE_PISTON_VALUE);
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new JoystickDrive());
  }

  public void setCANTalonsBrakeMode(boolean mode) {
    frontLeft.enableBrakeMode(mode);
    rearLeft.enableBrakeMode(mode);

    frontRight.enableBrakeMode(mode);
    rearRight.enableBrakeMode(mode);
  }
}
