package org.usfirst.frc.team3501.robot.commands.elevator;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.Constants.Direction;
import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This command makes the elevator move a specified distance using encoders on the robot and using a
 * feedback loop
 *
 * @param targetHeight: the height the elevator will move to, with respect to ground level
 * @param maxTimeOut: the maximum time this command will be allowed to run before being cut
 */
public class MoveToTarget extends CommandGroup {

  private Elevator elevator = Robot.getElevator();
  private double target;
  private double maxTimeOut;
  private double currentPos;
  private double distance;
  private static Direction direction;

  public MoveToTarget(double targetHeight, double maxTimeOut) {
    this.target = targetHeight;
    this.maxTimeOut = maxTimeOut;
    this.currentPos = elevator.getHeight();

    if (targetHeight > currentPos) { // current position is below target, so move upwards
      distance = targetHeight - currentPos;
      addSequential(new ElevatorDistance(distance, Constants.Direction.UP, maxTimeOut));
    } else if (targetHeight < currentPos) { // current position is above target, so move down
      distance = currentPos - targetHeight;
      addSequential(new ElevatorDistance(distance, Constants.Direction.DOWN, maxTimeOut));
    }
  }
}
