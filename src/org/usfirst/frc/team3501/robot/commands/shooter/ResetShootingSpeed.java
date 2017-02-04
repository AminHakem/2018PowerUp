package org.usfirst.frc.team3501.robot.commands.shooter;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command resets the speed at which the flywheel runs to the default
 * shooting speed
 *
 * post-condition: the shooting speed is reset
 */
public class ResetShootingSpeed extends Command {
  private Shooter shooter = Robot.getShooter();

  public ResetShootingSpeed() {
    requires(shooter);
  }

  @Override
  protected void initialize() {
    shooter.CURRENT_SHOOTING_SPEED = shooter.DEFAULT_SHOOTING_SPEED;
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override

  protected void interrupted() {
    end();
  }
}
