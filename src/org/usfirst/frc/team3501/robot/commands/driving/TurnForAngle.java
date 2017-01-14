package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command turns the robot for a specified angle in specified direction -
 * either left or right
 *
 * parameters:
 * angle: the robot will turn - in degrees
 * direction: the robot will turn - either right or left
 * motorVal: the motor input to set the motors to
 */
public class TurnForAngle extends Command {

  public TurnForAngle(double angle, Direction direction, double motorVal) {
    requires(Robot.getDriveTrain());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
