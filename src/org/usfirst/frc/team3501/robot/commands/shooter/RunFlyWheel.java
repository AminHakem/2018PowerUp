package org.usfirst.frc.team3501.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Runs the fly wheel at a given speed for a given time (sec)
 *
 * @param motorVal
 *          in ()
 * @param time
 *          in seconds
 * @author shaina
 */
public class RunFlyWheel extends Command {
  private double motorVal;
  private double time;

  public RunFlyWheel(double motorVal, double time) {
    this.motorVal = motorVal;
    this.time = time;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
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
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

}
