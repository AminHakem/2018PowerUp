package org.usfirst.frc.team3501.robot.commands.intake;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Stops running the intake.
 *
 * @author Meeta
 *
 */
public class StopIntake extends Command {
  public StopIntake() {
    requires(Robot.getIntake());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.getIntake().stopIntake();
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
    end();
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

}
