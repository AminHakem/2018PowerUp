package org.usfirst.frc.team3501.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command will run the index wheel motor continuously until the button
 * triggering it is released.
 *
 * pre-condition: This command must be run by a button in OI.
 * 
 * @param motorVal
 *          [-1,1]
 * @author shaina
 */
public class RunIndexWheelContinuous extends Command {
  private double motorVal;

  public RunIndexWheelContinuous(double motorVal) {
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
