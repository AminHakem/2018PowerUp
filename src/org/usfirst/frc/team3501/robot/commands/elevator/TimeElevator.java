package org.usfirst.frc.team3501.robot.commands.elevator;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.Constants.Direction;
import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;

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
public class TimeElevator extends Command {
  private Elevator elevator = Robot.getElevator();
  Timer timer;
  double motorVal, time;
  Direction direction;

  public TimeElevator(final double time, final double motorVal,
      Direction direction) {
    requires(elevator);

    timer = new Timer();
    this.time = time;
    this.motorVal = motorVal;
    this.direction = direction;
  }

  @Override
  protected void initialize() {
    timer.start();
  }

  @Override
  protected void execute() {

    System.out.println("Executing TimeElevator");

    if (direction == Constants.Direction.DOWN) {
      motorVal = -motorVal;
    }

    this.elevator.setMotorValue(motorVal);

  }

  @Override
  protected boolean isFinished() {
    boolean bTimeStatus = timer.get() >= time;
    System.out.println("Elevator time status" + bTimeStatus);
    return bTimeStatus;
  }

  @Override
  protected void end() {
    System.out.println("Stopping TimeElevator");
    this.elevator.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
