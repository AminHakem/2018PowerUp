package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.utils.PIDController;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command makes the robot drive a specified distance using encoders on the
 * robot and using a feedback loop
 *
 * parameters: distance the robot will move in inches motorVal: the motor input
 * to set the motors to
 */
public class DriveDistance extends Command {
  private DriveTrain driveTrain = Robot.getDriveTrain();
  private double maxTimeOut;
  private double target;
  private double zeroAngle;
  private Preferences prefs;
  private PIDController driveController;

  private double driveP;
  private double driveI;
  private double driveD;
  private double gyroP;

  public DriveDistance(double distance, double maxTimeOut) {
    requires(driveTrain);
    this.maxTimeOut = maxTimeOut;
    this.target = distance;

    this.driveP = driveTrain.driveP;
    this.driveI = driveTrain.driveI;
    this.driveD = driveTrain.driveD;
    this.gyroP = driveTrain.driveStraightGyroP;
    this.driveController = new PIDController(driveP, driveI, driveD);
    this.driveController.setDoneRange(1.0);
    this.driveController.setMaxOutput(1.0);
    this.driveController.setMinDoneCycles(5);
  }

  @Override
  protected void initialize() {
    this.driveTrain.resetEncoders();
    this.driveController.setSetPoint(this.target);
    this.zeroAngle = driveTrain.getAngle();
  }

  @Override
  protected void execute() {
    double xVal = gyroP * (driveTrain.getAngle() - zeroAngle);
    double yVal = driveController.calcPID(driveTrain.getRightEncoderDistance());

    double leftDrive = yVal - xVal;
    double rightDrive = yVal + xVal;
    this.driveTrain.setMotorValues(leftDrive, rightDrive);
  }

  @Override
  protected boolean isFinished() {
    return timeSinceInitialized() >= maxTimeOut
        || this.driveController.isDone();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
