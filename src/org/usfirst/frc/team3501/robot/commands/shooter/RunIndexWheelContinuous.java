package org.usfirst.frc.team3501.robot.commands.shooter;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command will run the index wheel motor continuously until the button
 * triggering it is released.
 *
 * pre-condition: This command must be run by a button in OI with
 * button.whileHeld(...).
 *
 * @author Shaina
 */
public class RunIndexWheelContinuous extends Command {
  private double motorVal;

  /**
   * See JavaDoc comment in class for details
   *
   * @param motorVal
   *          value range from -1 to 1
   */
  public RunIndexWheelContinuous(double motorVal) {
    this.motorVal = motorVal;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.getShooter().setIndexWheelMotorVal(motorVal);
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
    end();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

}
