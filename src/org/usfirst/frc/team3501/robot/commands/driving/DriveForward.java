package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.utils.PIDController;
import org.usfirst.frc.team3501.robot.utils.TimerUtil;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

  /**
   * @param distance: a positive value will cause the robot to move forwards, and a negative value
   *        will cause the robot to move backwards
   * @param maxTimeOut: the max time this command will be allowed to run on for
   */
  public DriveForward(double distance, double maxTimeOut) {
    requires(driveTrain);
    this.maxTimeOut = maxTimeOut;
    this.target = distance;
    if (target <= 20) {
      this.driveController = new PIDController(DriveTrain.driveStraightPShort,
          DriveTrain.driveStraightIShort, DriveTrain.driveStraightDShort);
    } else
      this.driveController = new PIDController(DriveTrain.driveStraightPLong,
          DriveTrain.driveStraightILong, DriveTrain.driveStraightDLong);
    this.driveController.setDoneRange(1.0);
    this.driveController.setMaxOutput(1.0);
    this.driveController.setMinDoneCycles(5);
    this.zeroAngle = this.driveTrain.getAngle();

    this.directionController = new PIDController(driveTrain.driveStraightGyroP, 0, 0);
    this.directionController.setSetPoint(zeroAngle);
  }

  @Override
  protected void initialize() {
    this.driveTrain.resetEncoders();
    this.driveTrain.resetGyro();
    this.driveController.setSetPoint(this.target);
    System.out.println(this.getName() + " initialized");
  }

  @Override
  protected void execute() {
    double ySpeed = driveController.calcPID(driveTrain.getFrontBackEncoderDistance());
    double rVal = directionController.calcPID(driveTrain.getAngle());
    this.driveTrain.mecanumDrive(0, -ySpeed, -rVal);
    SmartDashboard.putNumber("ySpeed", ySpeed);

  }

  @Override
  protected boolean isFinished() {
    return timeSinceInitialized() >= maxTimeOut || this.driveController.isDone();
  }

  @Override
  protected void end() {
    TimerUtil.printTime("DriveForward: ");
  }

  @Override
  protected void interrupted() {
    end();
  }
}
