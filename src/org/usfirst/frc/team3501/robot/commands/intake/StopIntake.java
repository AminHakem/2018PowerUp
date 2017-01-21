package org.usfirst.frc.team3501.robot.commands.intake;

import org.usfirst.frc.team3501.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Stops running the intake.
 *
 * @author Meeta
 *
 */
public class StopIntake extends Command {
  public StopIntake() {

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

  @Override
  protected boolean isFinished() {
    // TODO Auto-generated method stub
    return false;
  }

}
