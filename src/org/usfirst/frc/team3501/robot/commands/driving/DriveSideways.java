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
public class DriveSideways extends Command {
  private DriveTrain driveTrain = Robot.getDriveTrain();
  private double maxTimeOut;
  private double target;
  private Preferences prefs;
  private PIDController driveController;
  private double gyroP;

  /**
   * @param distance: a positive value will cause the robot to move right, and a negative value will
   *        cause the robot to move left
   * @param maxTimeOut: the max time this command will be allowed to run on for
   */
  public DriveSideways(double distance, double maxTimeOut) {
    requires(driveTrain);
    this.maxTimeOut = maxTimeOut;
    this.target = distance;
    this.gyroP = driveTrain.driveStraightGyroP;
    this.driveController = new PIDController(DriveTrain.driveSidewaysP, DriveTrain.driveSidewaysI,
        DriveTrain.driveSidewaysD);
    this.driveController.setDoneRange(1.0);
    this.driveController.setMaxOutput(1.0);
    this.driveController.setMinDoneCycles(5);
  }

  @Override
  protected void initialize() {
    this.driveTrain.resetEncoders();
    this.driveController.setSetPoint(this.target);
  }

  @Override
  protected void execute() {
    double xSpeed = driveController.calcPID(driveTrain.getRightLeftEncoderDistance());
    this.driveTrain.mecanumDrive(xSpeed, 0, 0);
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
