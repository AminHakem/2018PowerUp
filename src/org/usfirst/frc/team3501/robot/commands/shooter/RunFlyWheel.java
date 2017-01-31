package org.usfirst.frc.team3501.robot.commands.shooter;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command runs the fly wheel at a given speed for a given time. The fly
 * wheel is intended to shoot balls fed by the intake wheel.
 *
 * @author Shaina
 */
public class RunFlyWheel extends Command {
  private Shooter shooter = Robot.getShooter();
  Timer timer;
  private double time;

  /**
   * See JavaDoc comment in class for details
   *
   * @param motorVal
   *          value range from -1 to 1
   * @param time
   *          in seconds, amount of time to run fly wheel motor
   */
  public RunFlyWheel(double time) {
    requires(shooter);

    timer = new Timer();
    this.time = time;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.start();
    shooter.setFlyWheelMotorVal(shooter.CURRENT_SHOOTING_SPEED);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    shooter.stopFlyWheel();
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
