package org.usfirst.frc.team3501.robot.commands.shooter;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command runs index wheel at a given speed for given time in seconds.
 *
 * pre-condition: fly wheel is running at full speed to prepare for shooting
 * fuel
 *
 * @author Shaina
 */
public class RunIndexWheel extends Command {
  Timer timer;
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
  public RunIndexWheel(double motorVal, double time) {
    requires(Robot.getShooter());

    timer = new Timer();
    this.motorVal = motorVal;
    this.time = time;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.start();
    Robot.getShooter().setIndexWheelMotorVal(motorVal);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.getShooter().stopIndexWheel();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }

  @Override
  protected boolean isFinished() {
    return timer.get() >= time;
  }

}
