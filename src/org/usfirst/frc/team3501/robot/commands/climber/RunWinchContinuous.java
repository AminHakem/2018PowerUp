package org.usfirst.frc.team3501.robot.commands.climber;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command will run the winch motor continuously until the button
 * triggering it is released.
 *
 * pre-condition: This command must be run by a button in OI. The robot must be
 * attached to the rope.
 *
 * post-condition: Winch motor set to a specified speed.
 *
 * @param motorVal
 *          value range is from -1 to 1
 *
 * @author shivanighanta
 *
 */
public class RunWinchContinuous extends Command {
  private double motorVal;

  /**
   * 
   * @param motorVal
   */
  public RunWinchContinuous(double motorVal) {
    requires(Robot.getClimber());
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
