package org.usfirst.frc.team3501.robot.commands.climber;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command will run the winch motor continuously until the button
 * triggering it is released
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
    Robot.getClimber().setMotorValue(motorVal);

  }

  @Override
  protected void execute() {

  }

  @Override
  protected boolean isFinished() {
    return !Robot.getOI().toggleWinch.get();
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
