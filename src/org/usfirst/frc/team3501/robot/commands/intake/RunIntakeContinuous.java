package org.usfirst.frc.team3501.robot.commands.intake;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/***
 *
 * Runs the intake continuously when button is pressed, and when button is not
 * pressed does not run.
 *
 * Intended to be run inside a .whileHeld() call on a button in OI
 *
 * @author Meeta
 *
 */
public class RunIntakeContinuous extends Command {
  // create setter method for speed, use setSpeed method to do end() by setting
  // speed to 0

  public RunIntakeContinuous() {
    requires(Robot.getIntake());
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.getIntake().runIntake();
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
