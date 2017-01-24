package org.usfirst.frc.team3501.robot.commands.climber;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Stops the winch
 *
 * @author shivanighanta
 *
 */
public class StopWinch extends Command {

  public StopWinch() {
    requires(Robot.getDriveTrain());

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
    Robot.getDriveTrain().stop();

  }

  @Override
  protected void interrupted() {
    end();
  }
}