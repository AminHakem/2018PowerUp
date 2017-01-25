package org.usfirst.frc.team3501.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command decreases the speed at which the flywheel runs at by a fixed
 * constant
 *
 * post-condition: the shooting speed is decremented, such that whenever the
 * flywheel is run, it will run at the decreased shooting speed
 */
public class DecreaseShootingSpeed extends Command {
  public DecreaseShootingSpeed() {

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
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

  @Override
  protected boolean isFinished() {
    // TODO Auto-generated method stub
    return false;
  }

}
