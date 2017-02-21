package org.usfirst.frc.team3501.robot.commands.shooter;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command decreases the speed at which the flywheel runs at by a fixed
 * constant
 *
 * post-condition: the shooting speed is decremented, such that whenever the
 * flywheel is run, it will run at the decreased shooting speed
 */
public class DecreaseShootingSpeed extends Command {
  private Shooter shooter = Robot.getShooter();

  public DecreaseShootingSpeed() {

  }

  @Override
  protected void initialize() {
    shooter.decrementCurrentShootingSpeed();
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
