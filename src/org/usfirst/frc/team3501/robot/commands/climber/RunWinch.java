package org.usfirst.frc.team3501.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Runs the winch at a given time and motor value
 *
 * @author shivanighanta
 *
 */
public class RunWinch extends Command {
  private double time;
  private double motorVal;

  public RunWinch(double time, double motorVal) {
    this.time = time;
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
