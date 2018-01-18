package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Robot;
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
public class TimeDrive extends Command {
  Timer timer;
  double leftRearMotorVal, rightRearMotorVal, rightFrontMotorVal, leftFrontMotorVal, time;

  public TimeDrive(final double time, final double leftRearMotorVal, final double rightRearMotorVal,
      final double leftFrontMotorVal, final double rightFrontMotorVal) {
    requires(Robot.getDriveTrain());

    timer = new Timer();
    this.time = time;
    this.leftRearMotorVal = leftRearMotorVal;
    this.rightRearMotorVal = rightRearMotorVal;
    this.rightFrontMotorVal = rightFrontMotorVal;
    this.leftFrontMotorVal = leftFrontMotorVal;
  }

  @Override
  protected void initialize() {
    timer.start();
  }

  @Override
  protected void execute() {
    // Robot.getDriveTrain().setMotorValues(leftFrontMotorVal, leftRearMotorVal,
    // rightFrontMotorVal, rightRearMotorVal);

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
