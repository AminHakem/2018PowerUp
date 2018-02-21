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
public class DriveSideways extends Command {
  private DriveTrain driveTrain = Robot.getDriveTrain();
  private double maxTimeOut;
  private double target;
  private Preferences prefs;
  private PIDController driveController, directionController, yController;
  private double zeroAngle;

  /**
   * @param distance: a positive value will cause the robot to move right, and a negative value will
   *        cause the robot to move left
   * @param maxTimeOut: the max time this command will be allowed to run on for
   */
  public DriveSideways(double distance, double maxTimeOut) {
    requires(driveTrain);
    this.maxTimeOut = maxTimeOut;
    this.target = distance;
  }

  @Override
  protected void initialize() {
    this.driveTrain.resetEncoders();
    this.zeroAngle = this.driveTrain.getAngle();

    if (target <= 80)
      this.driveController = new PIDController(DriveTrain.driveSidewaysPShort,
          DriveTrain.driveSidewaysIShort, DriveTrain.driveSidewaysDShort);
    else
      this.driveController = new PIDController(DriveTrain.driveSidewaysPLong,
          DriveTrain.driveSidewaysILong, DriveTrain.driveSidewaysDLong);
    this.driveController.setDoneRange(5.0);
    this.driveController.setMaxOutput(1.0);
    this.driveController.setMinDoneCycles(5);
    this.driveController.setSetPoint(this.target);

    this.directionController = new PIDController(driveTrain.driveStraightGyroP, 0, 0);
    this.directionController.setSetPoint(zeroAngle);

    this.yController = new PIDController(0.06, 0.00001, 0);
    this.yController.setSetPoint(0);
  }

  @Override
  protected void execute() {
    double xSpeed = driveController.calcPID(driveTrain.getRightLeftEncoderDistance());
    double rVal = directionController.calcPID(driveTrain.getAngle());
    double ySpeed = yController.calcPID(driveTrain.getFrontBackEncoderDistance());
    this.driveTrain.mecanumDrive(xSpeed, ySpeed, rVal);
    SmartDashboard.putNumber("DriveSideways ySpeed", ySpeed);
  }

  @Override
  protected boolean isFinished() {
    return timeSinceInitialized() >= maxTimeOut || this.driveController.isDone();
  }

  @Override
  protected void end() {
    TimerUtil.printTime("Drive Sideways done: ");
  }

  @Override
  protected void interrupted() {
    end();
  }
}
