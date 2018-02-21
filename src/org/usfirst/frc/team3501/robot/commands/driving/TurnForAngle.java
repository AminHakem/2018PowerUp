package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.utils.PIDController;
import org.usfirst.frc.team3501.robot.utils.TimerUtil;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command turns the robot for a specified angle in specified direction - either left or right
 */
public class TurnForAngle extends Command {
  private DriveTrain driveTrain = Robot.getDriveTrain();

  private double maxTimeOut;
  private PIDController gyroController;

  private double target;
  private double gyroP;
  private double gyroI;
  private double gyroD;

  private double zeroAngle;

  /**
   * @param angle: a positive value will cause the robot to to turn right through the specified
   *        angle in degrees, and a negative value left
   * @param maxTimeOut: the max time this command will be allowed to run on for
   */
  public TurnForAngle(double angle, double maxTimeOut) {
    requires(Robot.getDriveTrain());
    this.maxTimeOut = maxTimeOut;
    this.target = angle;
  }

  @Override
  protected void initialize() {
    this.gyroController.setSetPoint(this.target);
    this.zeroAngle = driveTrain.getAngle();

    if (target > 90) {
      this.gyroP = driveTrain.largeTurnP;
      this.gyroI = driveTrain.largeTurnI;
      this.gyroD = driveTrain.largeTurnD;
    } else {
      this.gyroP = driveTrain.smallTurnP;
      this.gyroI = driveTrain.smallTurnI;
      this.gyroD = driveTrain.smallTurnD;
    }

    this.gyroController = new PIDController(this.gyroP, this.gyroI, this.gyroD);
    this.gyroController.setDoneRange(10);
    this.gyroController.setMinDoneCycles(5);
  }

  @Override
  protected void execute() {
    double zVal = this.gyroController.calcPID(this.driveTrain.getAngle() - this.zeroAngle);
    this.driveTrain.mecanumDrive(0, 0, zVal);
  }

  @Override
  protected boolean isFinished() {
    return timeSinceInitialized() >= maxTimeOut || gyroController.isDone();
  }

  @Override
  protected void end() {
    TimerUtil.printTime("TurnForAngle done: ");
  }

  @Override
  protected void interrupted() {
    end();
  }
}
