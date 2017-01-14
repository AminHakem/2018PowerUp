package org.usfirst.frc.team3501.robot.commands.shooter;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command stops the fly wheel. Do not call unless the trigger button has
 * been released.
 *
 *
 * @author Shaina
 */
public class StopFlyWheel extends Command {
  /**
   * This command stops the fly wheel.
   */
  public StopFlyWheel() {

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.getShooter().stopFlyWheel();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

}
