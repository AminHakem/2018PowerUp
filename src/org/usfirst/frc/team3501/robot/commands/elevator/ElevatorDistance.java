package org.usfirst.frc.team3501.robot.commands.elevator;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.Constants.Direction;
import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import org.usfirst.frc.team3501.robot.utils.PIDController;

import edu.wpi.first.wpilibj.command.Command;

/***
 * This command makes the elevator move a specified distance using a PID feedback loop that utilizes
 * the encoders attached to the elevator that tells us the height of the elevator
 *
 * @author David
 *
 * @param distance: the distance the elevator will move in inches
 * @param direction: the direction, up or down, the elevator will move in
 * @param maxTimeOut: the maximum time this command will be allowed to run before being cut
 */

public class ElevatorDistance extends Command {

  private Elevator elevator = Robot.getElevator();
  private PIDController elevatorController;

  private double maxTimeOut;
  private double target;
  private Direction direction;

  public ElevatorDistance(double distance, Direction direction, double maxTimeOut) {
    requires(elevator);
    this.maxTimeOut = maxTimeOut;
    this.target = distance;
    this.direction = direction;

    this.elevatorController =
        new PIDController(Elevator.ELEVATOR_P, Elevator.ELEVATOR_I, Elevator.ELEVATOR_D);
    this.elevatorController.setDoneRange(1.0);
    this.elevatorController.setMaxOutput(1.0);
    this.elevatorController.setMinDoneCycles(5);
  }

  @Override
  protected void initialize() {
    if (direction == Constants.Direction.UP)
      this.elevatorController.setSetPoint(this.target);
    else if (direction == Constants.Direction.DOWN)
      this.elevatorController.setSetPoint(-this.target);
  }

  @Override
  protected void execute() {
    double val = elevatorController.calcPID(elevator.getHeight());

    this.elevator.setMotorValue(val);
  }

  @Override
  protected boolean isFinished() {
    return timeSinceInitialized() >= maxTimeOut || this.elevatorController.isDone()
        || this.elevator.inBounds() == false;
  }

  @Override
  protected void end() {}

  @Override
  protected void interrupted() {
    end();
  }
}
