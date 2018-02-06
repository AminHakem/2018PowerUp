package org.usfirst.frc.team3501.robot.commands.elevator;

import org.usfirst.frc.team3501.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/***
 * This commands make the elevator climb for a specified time with the motors set at a specified
 * value between 1 and -1
 *
 * parameters: time: how long the elevator should climb for - in seconds motorVal: the motor input
 * to set the motors to
 *
 *
 */
public class TimeClimb extends Command {
  Timer timer;
  double motorVal, time;

  public TimeClimb(final double time, final double motorVal) {
    requires(Robot.getDriveTrain());

    timer = new Timer();
    this.time = time;
    this.motorVal = motorVal;
  }

  @Override
  protected void initialize() {
    timer.start();
  }

  @Override
  protected void execute() {
    Robot.getElevator().setMotorValue(motorVal);
  }

  @Override
  protected boolean isFinished() {
    return timer.get() >= time || Robot.getElevator().atIRFlag() == true;
  }

  @Override
  protected void end() {
    Robot.getElevator().stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
