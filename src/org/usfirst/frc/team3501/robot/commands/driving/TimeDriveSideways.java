package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/***
 * This commands make the robot drive for a specified time with the motors set at a specified value
 * between 1 and -1
 *
 * parameters: time: how long the robot should drive for - in seconds motorVal: the motor input to
 * set the motors to
 *
 *
 */
public class TimeDriveSideways extends Command {
  DriveTrain driveTrain = Robot.getDriveTrain();
  Timer timer;
  double motorVal, time;

  /**
   * @param time: the time in seconds the robot will move for before stopping
   * @param motorVal: the power at which the robot motors will move; a positive value will cause the
   *        robot to move right, and a negative value will cause the robot to move left
   */
  public TimeDriveSideways(final double time, final double motorVal) {
    requires(driveTrain);

    timer = new Timer();
    this.time = time;
  }

  @Override
  protected void initialize() {
    timer.start();
  }

  @Override
  protected void execute() {
    driveTrain.mecanumDrive(motorVal, 0, 0);
  }

  @Override
  protected boolean isFinished() {
    return timer.get() >= time;
  }

  @Override
  protected void end() {
    Robot.getDriveTrain().stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
