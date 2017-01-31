package org.usfirst.frc.team3501.robot.commands.shooter;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command increases the speed at which the flywheel runs at by a fixed
 * constant
 *
 * post-condition: the shooting speed is incremented, such that whenever the
 * flywheel is run, it will run at the increased shooting speed
 */
public class IncreaseShootingSpeed extends Command {
  private Shooter shooter = Robot.getShooter();

  public IncreaseShootingSpeed() {

  }

  @Override
  protected void initialize() {
    shooter.CURRENT_SHOOTING_SPEED += shooter.SHOOTING_SPEED_INCREMENT;
  }

  @Override
  protected void execute() {
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }

  @Override
  protected boolean isFinished() {

    return true;
  }

}
