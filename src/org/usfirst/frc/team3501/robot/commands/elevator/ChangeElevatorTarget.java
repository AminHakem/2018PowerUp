package org.usfirst.frc.team3501.robot.commands.elevator;

import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChangeElevatorTarget extends Command {

  private double target;
  Elevator elevator = Elevator.getElevator();

  public ChangeElevatorTarget(double target) {
    this.target = target;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    elevator.setTargetElavatorPos(elevator.getHeight());
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    elevator.setTargetElavatorPos(target);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {}
}
