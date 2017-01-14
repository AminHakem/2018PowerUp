package org.usfirst.frc.team3501.robot.commands.shooter;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command runs the fly wheel at a given speed for a given time. The fly
 * wheel is intended to shoot balls fed by the intake wheel.
 *
 * @author Shaina
 */
public class RunFlyWheel extends Command {
  Timer timer;
  private double motorVal;
  private double time;

  /**
   * See JavaDoc comment in class for details
   *
   * @param motorVal
   *          value range from -1 to 1
   * @param time
   *          in seconds, amount of time to run fly wheel motor
   */
  public RunFlyWheel(double motorVal, double time) {
    requires(Robot.getShooter());

    timer = new Timer();
    this.motorVal = motorVal;
    this.time = time;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.start();
    Robot.getShooter().setFlyWheelMotorVal(motorVal);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.getShooter().stopFlyWheel();
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
