package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.commands.driving.JoystickDrive;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class DriveTrain extends Subsystem {

  private static DriveTrain driveTrain;
  private final MecanumDrive robotDrive;

  private final WPI_TalonSRX frontLeft, frontRight, rearLeft, rearRight;
  private final SensorCollection frontBackEncoder, leftRightEncoder;

  // Set PID values, need to test mecanum wheels to find them
  public static double driveStraightP = 0.006, driveStraightI = 0.00115, driveStraightD = -0.002;
  public static double driveSidewaysP = 0.015, driveSidewaysI = 0.0015, driveSidewaysD = -0.002;
  public static double smallTurnP = 0.004, smallTurnI = 0.0013, smallTurnD = 0.005;
  public static double largeTurnP = .003, largeTurnI = .0012, largeTurnD = .006;
  public static double driveStraightGyroP = 0.01;

  // Calibration constants for encoders
  public static final double WHEEL_DIAMETER = 4; // inches
  public static final double ENCODER_PULSES_PER_REVOLUTION = 1024;
  public static final double INCHES_PER_PULSE =
      WHEEL_DIAMETER * Math.PI / ENCODER_PULSES_PER_REVOLUTION;
  private static final double ENCODER_DIST_CALIBRATION = 48.0 / 58.0;
  private static final double DEADZONE_RANGE = 0.1;
  private static final int RESET_ENC_MAXTIMEOUT = 3;

  private boolean fieldOriented, alignedWithCube;
  private double threadOutput;

  private ADXRS450_Gyro imu;

  private DriveTrain() {
    // MOTOR CONTROLLERS
    frontLeft = new WPI_TalonSRX(Constants.DriveTrain.FRONT_LEFT);
    frontRight = new WPI_TalonSRX(Constants.DriveTrain.FRONT_RIGHT);
    rearLeft = new WPI_TalonSRX(Constants.DriveTrain.REAR_LEFT);
    rearRight = new WPI_TalonSRX(Constants.DriveTrain.REAR_RIGHT);

    // Cast talons to speed controllers to instantiate mecanum drive
    SpeedControllerGroup m_left_rear = new SpeedControllerGroup(rearLeft);
    SpeedControllerGroup m_left_front = new SpeedControllerGroup(frontLeft);
    SpeedControllerGroup m_right_rear = new SpeedControllerGroup(rearRight);
    SpeedControllerGroup m_right_front = new SpeedControllerGroup(frontRight);
    robotDrive = new MecanumDrive(m_left_front, m_left_rear, m_right_front, m_right_rear);

    // Encoders
    frontBackEncoder = rearLeft.getSensorCollection();
    leftRightEncoder = rearRight.getSensorCollection();

    this.fieldOriented = false;
    this.alignedWithCube = false;

    try {
      this.imu = new ADXRS450_Gyro(Constants.DriveTrain.GYRO_PORT);
      this.imu.reset();
      this.imu.calibrate();
    } catch (NullPointerException e) {
      System.out.println("Gyro Null Pointer Exception");
      this.imu = null;
    }
  }

  /**
   * @return driveTrain if one does not already exist
   */
  public static DriveTrain getDriveTrain() {
    if (driveTrain == null) {
      driveTrain = new DriveTrain();
    }
    return driveTrain;
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new JoystickDrive());
  }

  // Driving / motor methods
  /**
   * Mecanum Drive - takes in the ySpeed, xSpeed, Z Rotation, and Gyro Angle as parameters and
   * enters those arguments in the driveCartesian method
   *
   * @param sidewaysSpeed
   * @param frontbackSpeed
   * @param rotation
   * @param is fieldOriented or not
   */
  public void mecanumDrive(final double sidewaysSpeed, final double frontbackSpeed,
      final double rotation) {
    if ((sidewaysSpeed < DEADZONE_RANGE && sidewaysSpeed > -DEADZONE_RANGE)
        && (frontbackSpeed < DEADZONE_RANGE && frontbackSpeed > -DEADZONE_RANGE)
        && (rotation < DEADZONE_RANGE && rotation > -DEADZONE_RANGE)) {
      robotDrive.stopMotor();
    }
    if (this.fieldOriented) {
      robotDrive.driveCartesian(-sidewaysSpeed, -frontbackSpeed, -rotation, -this.getAngle());
    } else {
      robotDrive.driveCartesian(-sidewaysSpeed, -frontbackSpeed, -rotation);
    }
  }

  /**
   * Stop the robot (set motor values to 0)
   */
  public void stop() {
    robotDrive.driveCartesian(0, 0, 0, 0);
  }

  public double getFrontLeftMotorPower() {
    return frontLeft.getMotorOutputVoltage();
  }

  public double getFrontRightMotorPower() {
    return frontRight.getMotorOutputVoltage();
  }

  public double getRearLeftMotorPower() {
    return rearLeft.getMotorOutputVoltage();
  }

  public double getRearRightMotorPower() {
    return rearRight.getMotorOutputVoltage();
  }

  // Encoders
  public double getRightLeftEncoderDistance() {
    return -ENCODER_DIST_CALIBRATION * leftRightEncoder.getQuadraturePosition() * INCHES_PER_PULSE
        / 4.0;
  }

  public double getFrontBackEncoderDistance() {
    return ENCODER_DIST_CALIBRATION * frontBackEncoder.getQuadraturePosition() * INCHES_PER_PULSE
        / 4.0;
  }

  public void resetEncoders() {
    frontBackEncoder.setQuadraturePosition(0, RESET_ENC_MAXTIMEOUT);
    leftRightEncoder.setQuadraturePosition(0, RESET_ENC_MAXTIMEOUT);
  }

  public double getRightLeftSpeed() {
    return leftRightEncoder.getQuadratureVelocity();
  }

  public double getFrontBackSpeed() {
    return frontBackEncoder.getQuadratureVelocity();
  }

  // Gyro
  public double getAngle() {
    if (imu != null)
      return this.imu.getAngle();
    else
      return 0;
  }

  /**
   * resets accumulator
   */
  public void resetGyro() {
    this.imu.reset();
  }

  // Methods to manage field oriented driving
  public boolean isFieldOriented() {
    return fieldOriented;
  }

  public void setFieldOriented(boolean fieldOriented) {
    this.fieldOriented = fieldOriented;
  }

  public void toggleFieldOriented() {
    this.setFieldOriented(!isFieldOriented());
  }

  // Vision for aligning robot
  public boolean isAlignedWithCube() {
    return alignedWithCube;
  }

  public void setAlignedWithCube(boolean alignedWithCube) {
    this.alignedWithCube = alignedWithCube;
  }

  public double getThreadOutput() {
    return threadOutput;
  }

  public void setThreadOutput(double threadOutput) {
    this.threadOutput = threadOutput;
  }

  public void toggleAlignedWithCube() {
    this.setAlignedWithCube(!alignedWithCube);
  }
}
