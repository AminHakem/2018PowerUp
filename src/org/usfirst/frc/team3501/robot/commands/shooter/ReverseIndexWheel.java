package org.usfirst.frc.team3501.robot.commands.shooter;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command reverses the index wheel at a given speed for given time in
 * seconds.
 *
 * @author shivanighanta
 */

public class ReverseIndexWheel extends Command {
  private double time;
  private double motorVal;

  /**
   * See JavaDoc comment in class for details
   *
   * @param motorVal
   *          value range from -1 to 1
   * @param time
   *          in seconds, amount of time to run index wheel motor
   */

  public ReverseIndexWheel(double time, double motorVal) {
    requires(Robot.getDriveTrain());
    this.time = time;
    this.motorVal = motorVal;
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    Robot.getShooter().setIndexWheelMotorVal(-motorVal);

  }

  @Override
  protected boolean isFinished() {
    return timeSinceInitialized() >= time;
  }

  @Override
  protected void end() {
    Robot.getShooter().stopIndexWheel();

  }

  @Override
  protected void interrupted() {
    end();
  }
}