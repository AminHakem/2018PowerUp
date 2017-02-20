package org.usfirst.frc.team3501.robot.commands.shooter;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command reverses the index wheel continuously when OI button managing
 * index wheel is pressed. The command will run the index wheel motor until the
 * button triggering it is released.
 *
 * Should only be run from the operator interface.
 *
 * pre-condition: This command must be run by a button in OI with
 * button.whileHeld(...).
 *
 * @author Rohan
 */
public class ReverseIndexWheelContinuous extends Command {
  private Shooter shooter = Robot.getShooter();

  /**
   * See JavaDoc comment in class for details
   *
   * @param motorVal
   *          value range from -1 to 1
   */
  public ReverseIndexWheelContinuous() {
    requires(shooter);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    shooter.setIndexWheelMotorVal(shooter.DEFAULT_INDEXING_SPEED * -1);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    shooter.stopIndexWheel();
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
