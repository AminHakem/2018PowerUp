package org.usfirst.frc.team3501.robot.commands.shooter;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command reverses the index wheel at a given speed for given time in
 * seconds.
 *
 * @author shivanighanta
 */

public class ReverseIndexWheel extends Command {
  private Shooter shooter = Robot.getShooter();
  private double time;

  /**
   * See JavaDoc comment in class for details
   *
   * @param motorVal
   *          value range from -1 to 1
   * @param time
   *          in seconds, amount of time to run index wheel motor
   */

  public ReverseIndexWheel(double time) {
    requires(Robot.getDriveTrain());
    this.time = time;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    shooter.reverseIndexWheel();

  }

  @Override
  protected boolean isFinished() {
    return timeSinceInitialized() >= time;
  }

  @Override
  protected void end() {
    shooter.stopIndexWheel();

  }

  @Override
  protected void interrupted() {
    end();
  }
}
