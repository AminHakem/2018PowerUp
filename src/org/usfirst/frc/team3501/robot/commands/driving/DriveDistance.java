package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.utils.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command makes the robot drive a specified distance using encoders on the robot and using a
 * feedback loop
 *
 * parameters: distance the robot will move in inches motorVal: the motor input to set the motors to
 */
public class DriveDistance extends Command {
  private DriveTrain driveTrain = Robot.getDriveTrain();
  private double maxTimeOut;
  private double target;
  private double angle;
  private Preferences prefs;
  private PIDController driveController;

  private double driveP;
  private double driveI;
  private double driveD;
  private double gyroP;

  public DriveDistance(double distance, double angle, double maxTimeOut) {
    requires(driveTrain);
    this.maxTimeOut = maxTimeOut;
    this.target = distance;
    this.angle = angle;

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
    this.angle += driveTrain.getAngle();
  }

  @Override
  protected void execute() {
    double xSpeed = Math.cos(driveTrain.getAngle());
    double ySpeed = Math.sin(driveTrain.getAngle());

    // double xVal = gyroP * (driveTrain.getAngle() - zeroAngle);
    // double yVal =
    // driveController.calcPID(driveTrain.getFrontBackEncoderDistance());


    this.driveTrain.mecanumDrive(ySpeed, xSpeed, rotation, gyroAngle);
  }

  @Override
  protected boolean isFinished() {
    return timeSinceInitialized() >= maxTimeOut || this.driveController.isDone();
  }

  @Override
  protected void end() {}

  @Override
  protected void interrupted() {
    end();
  }
}
