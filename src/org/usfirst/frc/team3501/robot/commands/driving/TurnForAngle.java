package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.Constants.Direction;
import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.utils.PIDController;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command turns the robot for a specified angle in specified direction -
 * either left or right
 *
 * parameters: angle: the robot will turn - in degrees direction: the robot will
 * turn - either right or left maxTimeOut: the max time this command will be
 * allowed to run on for
 */
public class TurnForAngle extends Command {
  private DriveTrain driveTrain = Robot.getDriveTrain();

  Direction direction;
  private double maxTimeOut;
  private PIDController gyroController;

  private double target;
  private double gyroP;
  private double gyroI;
  private double gyroD;

  private double zeroAngle;

  public TurnForAngle(double angle, Direction direction, double maxTimeOut) {
    requires(Robot.getDriveTrain());
    this.direction = direction;
    this.maxTimeOut = maxTimeOut;
    this.target = Math.abs(angle);
    this.zeroAngle = driveTrain.getAngle();

    this.gyroP = driveTrain.turnP;
    this.gyroI = driveTrain.turnI;
    this.gyroD = driveTrain.turnD;

    this.gyroController = new PIDController(this.gyroP, this.gyroI, this.gyroD);
    this.gyroController.setDoneRange(1);
    this.gyroController.setMinDoneCycles(5);
  }

  @Override
  protected void initialize() {
    this.driveTrain.resetEncoders();
    this.gyroController.setSetPoint(this.target);
  }

  @Override
  protected void execute() {
    double xVal = 0;

    xVal = this.gyroController
        .calcPID(Math.abs(this.driveTrain.getAngle() - this.zeroAngle));

    double leftDrive = 0;
    double rightDrive = 0;
    if (direction == Constants.Direction.RIGHT) {
      leftDrive = xVal;
      rightDrive = -xVal;
    } else if (direction == Constants.Direction.LEFT) {
      leftDrive = -xVal;
      rightDrive = xVal;
    }

    this.driveTrain.setMotorValues(leftDrive, rightDrive);
  }

  @Override
  protected boolean isFinished() {
    return timeSinceInitialized() >= maxTimeOut || gyroController.isDone();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
