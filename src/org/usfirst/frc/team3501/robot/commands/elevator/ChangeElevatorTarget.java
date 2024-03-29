package org.usfirst.frc.team3501.robot.commands.elevator;

import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import org.usfirst.frc.team3501.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChangeElevatorTarget extends Command {

  private double target;
  Elevator elevator = Elevator.getElevator();
  Intake intake = Intake.getIntake();
  public ChangeElevatorTarget(double target) {
    this.target = target;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(this.target>=elevator.SWITCH_POS) intake.setDown(true);
    if(this.target==elevator.BOTTOM_POS) intake.setDown(true);
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
