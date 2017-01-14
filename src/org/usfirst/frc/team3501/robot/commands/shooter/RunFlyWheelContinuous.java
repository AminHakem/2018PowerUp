package org.usfirst.frc.team3501.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Runs fly wheel continuously when corresponding button pressed
 *
 * @param motorVal
 *          motor value
 * @author shaina
 */
public class RunFlyWheelContinuous extends Command {
  private double motorVal;

  public RunFlyWheelContinuous(double motorVal) {
    this.motorVal = motorVal;
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
