package org.usfirst.frc.team3501.robot.commands.intake;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Reverses the intake until the button triggering this command is released
 *
 * pre-condition: button is pressed
 */
public class ReverseIntakeContinuous extends Command {

  public ReverseIntakeContinuous() {
    requires(Robot.getIntake());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.getIntake().runReverseIntake();

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
    Robot.getIntake().stopIntake();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
