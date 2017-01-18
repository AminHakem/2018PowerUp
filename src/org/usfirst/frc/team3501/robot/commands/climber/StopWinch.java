package org.usfirst.frc.team3501.robot.commands.climber;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command stops the winch
 *
 * post-condition: the motor values are set to 0.
 *
 * @author shivanighanta
 *
 */
public class StopWinch extends Command {

  public StopWinch() {
    requires(Robot.getClimber());

  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
    Robot.getClimber().stop();

  }

  @Override
  protected void interrupted() {
    end();
  }
}
