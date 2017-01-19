package org.usfirst.frc.team3501.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Runs the winch continuously at a given motor value
 *
 * @author shivanighanta
 *
 */
public class RunWinchContinuous extends Command {
  private double motorVal;

  public RunWinchContinuous(double motorVal) {
    this.motorVal = motorVal;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {

  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {

  }

  @Override
  protected void interrupted() {
  }
}