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
public class DriveForward extends Command {
  private DriveTrain driveTrain = Robot.getDriveTrain();
  private double maxTimeOut;
  private double target;
  private Preferences prefs;
  private PIDController driveController, directionController;
  private double zeroAngle;
  private double driveP;
  private double driveI;
  private double driveD;

  public DriveForward(double distance, double maxTimeOut) {
    requires(driveTrain);
    this.maxTimeOut = maxTimeOut;
    this.target = distance;

    this.driveP = driveTrain.driveP;
    this.driveI = driveTrain.driveI;
    this.driveD = driveTrain.driveD;
    this.driveController = new PIDController(driveP, driveI, driveD);
    this.driveController.setDoneRange(1.0);
    this.driveController.setMaxOutput(1.0);
    this.driveController.setMinDoneCycles(5);
    this.zeroAngle = this.driveTrain.getAngle();

    this.directionController = new PIDController(driveTrain.driveStraightGyroP, 0, 0);
    this.directionController.setSetPoint(zeroAngle);
  }

  @Override
  protected void initialize() {
    // this.driveTrain.resetEncoders();
    this.driveController.setSetPoint(this.target);
  }

  @Override
  protected void execute() {
    double ySpeed = driveController.calcPID(driveTrain.getFrontBackEncoderDistance());
    double rVal = directionController.calcPID(driveTrain.getAngle());

    this.driveTrain.mecanumDrive(ySpeed, 0, rVal);
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
