package org.usfirst.frc.team3501.robot.commands.climber;

import org.usfirst.frc.team3501.robot.Robot;

import com.sun.glass.ui.Timer;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command runs the winch for a given motor value and time in seconds
 *
 * @param motorVal
 *          value range is from -1 to 1
 * @param time
 *          in seconds
 * @author shivanighanta
 *
 */
public class RunWinch extends Command {
  Timer timer;
  private double time;
  private double motorVal;

  public RunWinch(double time, double motorVal) {
    requires(Robot.getClimber());
    this.time = time;
    this.motorVal = motorVal;
  }

  @Override
  protected void initialize() {
    timer.start();
    Robot.getClimber().setMotorValue(motorVal);
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
    Robot.getClimber().stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
