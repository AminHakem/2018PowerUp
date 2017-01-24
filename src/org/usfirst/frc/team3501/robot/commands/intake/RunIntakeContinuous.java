package org.usfirst.frc.team3501.robot.commands.intake;

import org.usfirst.frc.team3501.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/***
 *
 * Runs the intake continuously.
 *
 * @author Meeta
 *
 */
public class RunIntakeContinuous extends Command {
  // create setter method for speed, use setSpeed method to do end() by setting
  // speed to 0

  public RunIntakeContinuous() {

  }

  @Override
  protected boolean isFinished() {
    // TODO Auto-generated method stub
    return true;
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
    Intake.intake.setSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }

}