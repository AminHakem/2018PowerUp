package org.usfirst.frc.team3501.robot.commands.climber;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Stops the winch
 *
 * @author shivanighanta
 *
 */
public class StopWinch extends Command {
  Climber climber = Robot.getClimber();

  public StopWinch() {
    requires(climber);
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
    climber.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
