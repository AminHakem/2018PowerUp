package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TimeDrive extends Command {
  Timer timer;
  double motorVal, time;

  public TimeDrive(final double time, final double motorVal) {
    requires(Robot.getDriveTrain());

    timer = new Timer();
    this.time = time;
    this.motorVal = motorVal;
  }

  @Override
  protected void initialize() {
    timer.start();
    Robot.getDriveTrain().setMotorValues(motorVal, motorVal);
  }

  @Override
  protected void execute() {
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
