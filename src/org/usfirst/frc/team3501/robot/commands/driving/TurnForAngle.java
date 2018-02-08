package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Constants.Direction;
import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.utils.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command turns the robot for a specified angle in specified direction - either left or right
 *
 * parameters: angle: the robot will turn - negative angle is left positive angle is right
 * or left maxTimeOut: the max time this command will be allowed to run on for
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

  public TurnForAngle(double angle, double maxTimeOut) {
    requires(Robot.getDriveTrain());
    this.maxTimeOut = maxTimeOut;
    this.target = angle;

    if (angle > 90) {
      this.gyroP = driveTrain.largeTurnP;
      this.gyroI = driveTrain.largeTurnI;
      this.gyroD = driveTrain.largeTurnD;
    } else {
      this.gyroP = driveTrain.smallTurnP;
      this.gyroI = driveTrain.smallTurnI;
      this.gyroD = driveTrain.smallTurnD;
    }

    this.gyroController = new PIDController(this.gyroP, this.gyroI, this.gyroD);
    this.gyroController.setDoneRange(1);
    this.gyroController.setMinDoneCycles(5);
  }

  @Override
  protected void initialize() {
    this.driveTrain.resetEncoders();
    this.gyroController.setSetPoint(this.target);
    this.zeroAngle = driveTrain.getAngle();
  }

  @Override
  protected void execute() {
    double zVal = this.gyroController.calcPID(this.driveTrain.getAngle() - this.zeroAngle);
    System.out.println("Angle: "+ driveTrain.getAngle());
    this.driveTrain.mecanumDrive(0, 0, -zVal);
  }

  @Override
  protected boolean isFinished() {
    return timeSinceInitialized() >= maxTimeOut || gyroController.isDone();
  }

  @Override
  protected void end() {}

  @Override
  protected void interrupted() {
    end();
  }
}
