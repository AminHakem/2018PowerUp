package org.usfirst.frc.team3501.robot.commands.shooter;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command runs the fly wheel continuously when OI button managing fly
 * wheel is pressed. The command will run the fly wheel motor until the button
 * triggering it is released.
 *
 * Should only be run from the operator interface.
 *
 * pre-condition: This command must be run by a button in OI, with
 * button.whileHeld(...).
 *
 * @author Shaina
 */
public class RunFlyWheelContinuous extends Command {
  private Shooter shooter = Robot.getShooter();

  /**
   * See JavaDoc comment in class for details
   *
   * @param motorVal
   *          value range from -1 to 1
   */
  public RunFlyWheelContinuous() {
    requires(shooter);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    shooter.setFlyWheelMotorVal(shooter.CURRENT_SHOOTING_SPEED);
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
